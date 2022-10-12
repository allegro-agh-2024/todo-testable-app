package pl.allegro.agh.distributedsystems.todo.domain.todos

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import pl.allegro.agh.distributedsystems.todo.infrastructure.todos.InMemoryTodosRepository
import strikt.api.expectThat
import strikt.assertions.hasSize

class TodosServiceTest {
    private val repository = InMemoryTodosRepository()
    private val service = TodosService(repository)

    @AfterEach
    fun tearDown() {
        repository.clear()
    }

    @Test
    fun `generate unique ids on save`() {
        val uniqueIds = (1..100)
            .map { service.save("user", "todo name $it") }
            .map { it.id }
            .toSet()

        expectThat(uniqueIds).hasSize(100)
    }
}
