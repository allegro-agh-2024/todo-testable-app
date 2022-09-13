package pl.allegro.agh.distributedsystems.todo.infrastructure.todos

import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosRepository
import java.util.concurrent.CopyOnWriteArrayList

class InMemoryTodosRepository : TodosRepository {
    private val todos: MutableList<String> = CopyOnWriteArrayList()

    override fun getAll(): List<String> = todos

    override fun save(name: String) {
        todos += name
    }

    fun clear() = todos.clear()
}
