package pl.allegro.agh.distributedsystems.todo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosRepository
import pl.allegro.agh.distributedsystems.todo.infrastructure.todos.InMemoryTodosRepository

@Configuration
class TodosConfiguration {

    @Bean
    fun todosRepository(): TodosRepository = InMemoryTodosRepository()
}