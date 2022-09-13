package pl.allegro.agh.distributedsystems.todo.domain.todos

interface TodosRepository {
    fun getAll(): List<String>
    fun save(name: String)
}
