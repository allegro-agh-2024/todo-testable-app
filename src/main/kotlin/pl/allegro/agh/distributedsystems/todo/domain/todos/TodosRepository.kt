package pl.allegro.agh.distributedsystems.todo.domain.todos

interface TodosRepository {
    fun getAll(user: String): List<String>
    fun save(user: String, name: String)
}
