package pl.allegro.agh.distributedsystems.todo.infrastructure.todos

import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosRepository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

class InMemoryTodosRepository : TodosRepository {
    private val todos: MutableMap<String, MutableList<String>> = ConcurrentHashMap()

    override fun getAll(user: String): List<String> = todos[user] ?: emptyList()

    override fun save(user: String, name: String) {
        todos.computeIfAbsent(user) { CopyOnWriteArrayList() } += name
    }

    fun clear() = todos.clear()
}
