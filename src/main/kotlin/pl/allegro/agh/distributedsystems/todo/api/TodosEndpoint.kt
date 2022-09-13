package pl.allegro.agh.distributedsystems.todo.api

import org.springframework.web.bind.annotation.*
import pl.allegro.agh.distributedsystems.todo.domain.todos.Todo
import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosRepository
import java.security.Principal
import java.util.*

@RestController
@RequestMapping("/todos")
class TodosEndpoint(
    private val todosRepository: TodosRepository,
) {

    @GetMapping(produces = ["application/json"])
    fun todosList(principal: Principal) =
        TodosResponseDto(todosRepository.getAll(principal.name).map { TodoDto(it.name, "") })

    @PostMapping(consumes = ["application/json"])
    fun saveTodo(principal: Principal, @RequestBody saveTodoDto: SaveTodoDto): TodoDto {
        val todo = Todo(
            id = UUID.randomUUID().toString(),
            user = principal.name,
            name = saveTodoDto.name,
        )
        return todosRepository.save(todo).let {
            TodoDto(
                name = it.name,
                id = it.id,
            )
        }
    }
}

data class TodosResponseDto(val todos: List<TodoDto>)

data class SaveTodoDto(val name: String)

data class TodoDto(
    val name: String,
    val id: String,
)
