package pl.allegro.agh.distributedsystems.todo

import pl.allegro.agh.distributedsystems.todo.setup.MongoInitializer

fun main(args: Array<String>) {
    createSpringApplication()
        .apply {
            addInitializers(MongoInitializer())
            setAdditionalProfiles("local")
        }
        .run(*args)
}
