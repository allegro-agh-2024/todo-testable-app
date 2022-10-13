package pl.allegro.agh.distributedsystems.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoTestableApp

fun main(args: Array<String>) {
    runApplication<TodoTestableApp>(*args)
}
