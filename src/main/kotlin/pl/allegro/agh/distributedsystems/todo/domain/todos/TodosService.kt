package pl.allegro.agh.distributedsystems.todo.domain.todos

import java.util.*

class TodosService(
    private val todosRepository: TodosRepository,
) {
    fun getAll(user: String): List<Todo> = todosRepository.getAll(user)

    fun save(user: String, name: String): Todo = Todo(
        id = UUID.randomUUID().toString(),
        user = user,
        name = name,
    ).let(todosRepository::save)
}
