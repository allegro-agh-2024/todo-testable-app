package pl.allegro.agh.distributedsystems.todo.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties::class)
class SecurityConfiguration {

    @Bean
    fun userDetailsService(properties: SecurityProperties): UserDetailsService = InMemoryUserDetailsManager()
        .apply {
            properties.users
                .map {
                    User.withUsername(it.username)
                        .password(it.password)
                        .roles("USER")
                        .build()
                }
                .forEach { createUser(it) }
        }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain = http
        .csrf().disable()
        .authorizeRequests {
            it.antMatchers("/todos/**").authenticated()
        }
        .httpBasic()
        .and()
        .build()
}

@ConstructorBinding
@ConfigurationProperties("app")
data class SecurityProperties(
    val users: List<User> = emptyList(),
) {
    data class User(
        val username: String,
        val password: String,
    )
}
