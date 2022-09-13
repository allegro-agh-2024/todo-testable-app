package pl.allegro.agh.distributedsystems.todo.setup

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

class MongoInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        mongoContainer.start()

        TestPropertyValues.of(
            "spring.data.mongodb.uri=${mongoContainer.replicaSetUrl}"
        ).applyTo(applicationContext.environment)
    }

    private companion object {
        val imageName: DockerImageName = DockerImageName.parse("mongo:5.0.12")
        val mongoContainer = MongoDBContainer(imageName)
    }
}
