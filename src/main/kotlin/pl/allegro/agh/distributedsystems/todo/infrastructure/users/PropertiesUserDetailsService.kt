package pl.allegro.agh.distributedsystems.todo.infrastructure.users

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import pl.allegro.agh.distributedsystems.todo.domain.users.User
import pl.allegro.agh.distributedsystems.todo.domain.users.UserRepository
import org.springframework.security.core.userdetails.User as SpringUser

class PropertiesUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByName(username)?.toSpringDto()
            ?: throw UsernameNotFoundException("User not found")

    private fun User.toSpringDto() =
        SpringUser.withUsername(username)
            .password(password)
            .roles("USER")
            .build()
}
