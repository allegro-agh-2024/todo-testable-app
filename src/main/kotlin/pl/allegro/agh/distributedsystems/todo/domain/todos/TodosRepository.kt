package pl.allegro.agh.distributedsystems.todo.domain.todos

interface TodosRepository {
    fun getAll(user: String): List<Todo>
    fun save(todo: Todo): Todo
}

data class Todo(
    val id: String,
    val user: String,
    val name: String,
)
