package pl.allegro.agh.distributedsystems.todo.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.allegro.agh.distributedsystems.todo.domain.todos.Todo
import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosService
import java.security.Principal

@RestController
@RequestMapping("/todos")
class TodosEndpoint(
    private val todosService: TodosService,
) {

    @GetMapping(produces = ["application/json"])
    fun todosList(principal: Principal) =
        todosService.getAll(principal.name).toDto()

    @PostMapping(consumes = ["application/json"])
    fun saveTodo(principal: Principal, @RequestBody saveTodoDto: SaveTodoDto) =
        todosService.save(principal.name, saveTodoDto.name).toDto()

    private fun List<Todo>.toDto() = TodosResponseDto(map { it.toDto() })
    private fun Todo.toDto() = TodoDto(name, id)

    @ExceptionHandler(TodosService.CannotSaveException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Cannot save todo")
    fun handleSaveException() {
    }
}

data class TodosResponseDto(val todos: List<TodoDto>)

data class SaveTodoDto(val name: String)

data class TodoDto(
    val name: String,
    val id: String,
)
