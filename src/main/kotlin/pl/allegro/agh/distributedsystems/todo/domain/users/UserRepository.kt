package pl.allegro.agh.distributedsystems.todo.domain.users

interface UserRepository {
    fun findByName(username: String): User?
}

data class User(
    val username: String,
    val password: String,
)
