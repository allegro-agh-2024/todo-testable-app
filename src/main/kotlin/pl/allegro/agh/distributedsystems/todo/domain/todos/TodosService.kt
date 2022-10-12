package pl.allegro.agh.distributedsystems.todo.domain.todos

import java.util.*

class TodosService(
    private val todosRepository: TodosRepository,
) {
    fun getAll(user: String): List<Todo> = todosRepository.getAll(user)

    fun save(user: String, name: String): Todo = when {
        name.length >= 100 -> throw CannotSaveException("Name is too long")
        name.length <= 4 -> throw CannotSaveException("Name is too short")
        else -> Todo(
            id = UUID.randomUUID().toString(),
            user = user,
            name = name,
        ).let(todosRepository::save)
    }

    class CannotSaveException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause)
}
