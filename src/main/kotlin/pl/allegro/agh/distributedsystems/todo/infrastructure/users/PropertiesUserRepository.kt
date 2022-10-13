package pl.allegro.agh.distributedsystems.todo.infrastructure.users

import pl.allegro.agh.distributedsystems.todo.config.UserProperties
import pl.allegro.agh.distributedsystems.todo.domain.users.User
import pl.allegro.agh.distributedsystems.todo.domain.users.UserRepository

class PropertiesUserRepository(
    properties: UserProperties,
) : UserRepository {
    private val users = properties.users
        .map {
            User(
                username = it.username,
                password = it.password,
                status = User.Status.valueOf(it.status),
            )
        }
        .associateBy { it.username }

    override fun findByName(username: String): User? = users[username]
}
