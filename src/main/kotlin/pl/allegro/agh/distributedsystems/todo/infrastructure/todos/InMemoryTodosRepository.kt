package pl.allegro.agh.distributedsystems.todo.infrastructure.todos

import pl.allegro.agh.distributedsystems.todo.domain.todos.Todo
import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosRepository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

class InMemoryTodosRepository : TodosRepository {
    private val todos: MutableMap<String, MutableList<Todo>> = ConcurrentHashMap()

    override fun getAll(user: String): List<Todo> = todos[user] ?: emptyList()

    override fun save(todo: Todo): Todo {
        todos.computeIfAbsent(todo.user) { CopyOnWriteArrayList() } += todo
        return todo
    }

    fun clear() = todos.clear()
}
