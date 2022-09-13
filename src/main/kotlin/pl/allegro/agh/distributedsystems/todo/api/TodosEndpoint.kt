package pl.allegro.agh.distributedsystems.todo.api

import org.springframework.web.bind.annotation.*
import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosRepository

@RestController
@RequestMapping("/todos")
class TodosEndpoint(
    private val todosRepository: TodosRepository,
) {

    @GetMapping(produces = ["application/json"])
    fun todosList() = TodosResponseDto(todosRepository.getAll())

    @PostMapping(consumes = ["application/json"])
    fun saveTodo(@RequestBody saveTodoDto: SaveTodoDto) {
        todosRepository.save(saveTodoDto.name)
    }
}

data class TodosResponseDto(val todos: List<String>)

data class SaveTodoDto(val name: String)
