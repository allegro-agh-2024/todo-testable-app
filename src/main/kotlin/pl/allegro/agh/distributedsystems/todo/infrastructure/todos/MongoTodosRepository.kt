package pl.allegro.agh.distributedsystems.todo.infrastructure.todos

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import pl.allegro.agh.distributedsystems.todo.domain.todos.Todo
import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosRepository

class MongoTodosRepository(
    private val repository: MongoTodosRepositoryCrud,
) : TodosRepository {
    override fun getAll(user: String): List<Todo> =
        repository.findAllByUserEquals(user).map { it.toDomain() }

    override fun save(todo: Todo): Todo =
        repository.save(todo.toEntity()).toDomain()

    private fun TodoEntity.toDomain() = Todo(
        id = id,
        user = user,
        name = name,
    )

    private fun Todo.toEntity() = TodoEntity(
        id = id,
        user = user,
        name = name,
    )
}

interface MongoTodosRepositoryCrud : MongoRepository<TodoEntity, String> {
    fun findAllByUserEquals(user: String): List<TodoEntity>
}

@Document("todos_v1")
data class TodoEntity(
    @Id val id: String,
    val user: String,
    val name: String,
)
