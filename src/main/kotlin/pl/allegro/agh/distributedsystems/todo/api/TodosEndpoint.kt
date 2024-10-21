package pl.allegro.agh.distributedsystems.todo.api

import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.allegro.agh.distributedsystems.todo.domain.todos.Todo
import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosService
import java.security.Principal

@RestController
@RequestMapping("/todos")
class TodosEndpoint(
    private val todosService: TodosService,
) {

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun todosList(principal: Principal) =
        todosService.getAll(principal.name).toDto()

    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun saveTodo(principal: Principal, @RequestBody saveTodoDto: SaveTodoDto) =
        todosService.save(principal.name, saveTodoDto.name).toDto()

    private fun List<Todo>.toDto() = TodosResponseDto(map { it.toDto() })
    private fun Todo.toDto() = TodoDto(name, id)

    @ExceptionHandler(TodosService.CannotSaveException::class)
    fun handleSaveException(e: TodosService.CannotSaveException): ResponseEntity<Map<String, Any>> {
        val message = e.message ?: "Cannot save todo"
        return ResponseEntity.badRequest().body(mapOf("error" to message))
    }
}

data class TodosResponseDto(val todos: List<TodoDto>)

data class SaveTodoDto(val name: String)

data class TodoDto(
    val name: String,
    val id: String,
)
