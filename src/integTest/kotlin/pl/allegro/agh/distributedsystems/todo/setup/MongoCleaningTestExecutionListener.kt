package pl.allegro.agh.distributedsystems.todo.setup

import org.bson.Document
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestExecutionListener

class MongoCleaningTestExecutionListener : TestExecutionListener {
    override fun afterTestMethod(testContext: TestContext) {
        val mongoTemplate = testContext.applicationContext.getBean(MongoTemplate::class.java)
        mongoTemplate.collectionNames.forEach {
            mongoTemplate.getCollection(it).deleteMany(Document())
        }
    }
}
