package pl.allegro.agh.distributedsystems.todo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosRepository
import pl.allegro.agh.distributedsystems.todo.domain.todos.TodosService
import pl.allegro.agh.distributedsystems.todo.domain.users.UserRepository
import pl.allegro.agh.distributedsystems.todo.infrastructure.todos.MongoTodosRepository
import pl.allegro.agh.distributedsystems.todo.infrastructure.todos.MongoTodosRepositoryCrud

@Configuration
class TodosConfiguration {

    @Bean
    fun todosService(
        todosRepository: TodosRepository,
        userRepository: UserRepository,
    ): TodosService =
        TodosService(todosRepository, userRepository)

    @Bean
    fun todosRepository(repository: MongoTodosRepositoryCrud): TodosRepository = MongoTodosRepository(repository)
}
