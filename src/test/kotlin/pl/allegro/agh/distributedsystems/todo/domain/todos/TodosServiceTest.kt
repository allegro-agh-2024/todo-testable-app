package pl.allegro.agh.distributedsystems.todo.domain.todos

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import pl.allegro.agh.distributedsystems.todo.infrastructure.todos.InMemoryTodosRepository
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.message

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

    @Nested
    inner class `todo length` {

        @ParameterizedTest
        @CsvSource(
            "4,   Name is too short",
            "100, Name is too long",
        )
        fun `reject too long`(length: Int, message: String) {
            expectThrows<TodosService.CannotSaveException> {
                service.save("user", "l".repeat(length))
            }.message.isEqualTo(message)
        }

        @ParameterizedTest
        @ValueSource(ints = [5, 99])
        fun `accept below threshold`(length: Int) {
            service.save("user", "l".repeat(length))
            expectThat(service.getAll("user")).hasSize(1)
        }
    }
}
