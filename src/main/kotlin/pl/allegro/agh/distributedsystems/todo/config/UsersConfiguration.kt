package pl.allegro.agh.distributedsystems.todo.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.allegro.agh.distributedsystems.todo.domain.users.UserRepository
import pl.allegro.agh.distributedsystems.todo.infrastructure.users.PropertiesUserRepository

@Configuration
@EnableConfigurationProperties(UserProperties::class)
class UsersConfiguration(
    private val properties: UserProperties,
) {

    @Bean
    fun userRepository(): UserRepository = PropertiesUserRepository(properties)
}

@ConstructorBinding
@ConfigurationProperties("app")
data class UserProperties(
    val users: List<User> = emptyList(),
) {
    data class User(
        val username: String,
        val password: String,
        val status: String,
    )
}
