package pl.allegro.agh.distributedsystems.todo.api

import org.springframework.web.bind.annotation.*
import java.util.concurrent.CopyOnWriteArrayList

@RestController
@RequestMapping("/todos")
class TodosEndpoint {
    private val todos: MutableList<String> = CopyOnWriteArrayList()

    @GetMapping(produces = ["application/json"])
    fun todosList() = TodosResponseDto(todos)

    @PostMapping(consumes = ["application/json"])
    fun saveTodo(@RequestBody saveTodoDto: SaveTodoDto) {
        todos += saveTodoDto.name
    }

    fun clear() = todos.clear()
}

data class TodosResponseDto(val todos: List<String>)

data class SaveTodoDto(val name: String)
