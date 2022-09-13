package pl.allegro.agh.distributedsystems.todo.api

import org.springframework.web.bind.annotation.*
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
        TodosResponseDto(todosRepository.getAll(principal.name).map { TodoDto(it, "") })

    @PostMapping(consumes = ["application/json"])
    fun saveTodo(principal: Principal, @RequestBody saveTodoDto: SaveTodoDto): TodoDto {
        val id = UUID.randomUUID().toString()
        todosRepository.save(principal.name, saveTodoDto.name)
        return TodoDto(saveTodoDto.name, id)
    }
}

data class TodosResponseDto(val todos: List<TodoDto>)

data class SaveTodoDto(val name: String)

data class TodoDto(
    val name: String,
    val id: String,
)
