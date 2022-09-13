package pl.allegro.agh.distributedsystems.todo

import pl.allegro.agh.distributedsystems.todo.setup.MongoInitializer

fun main(args: Array<String>) {
    val application = createSpringApplication()
    application.addInitializers(MongoInitializer())
    application.run(*args)
}
