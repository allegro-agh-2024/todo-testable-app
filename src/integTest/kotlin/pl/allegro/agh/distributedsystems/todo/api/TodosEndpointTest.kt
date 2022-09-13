package pl.allegro.agh.distributedsystems.todo.api

import org.hamcrest.Matcher
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.emptyIterable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import pl.allegro.agh.distributedsystems.todo.infrastructure.todos.InMemoryTodosRepository

@SpringBootTest
@AutoConfigureMockMvc
class TodosEndpointTest(@Autowired private val mockMvc: MockMvc) {

    @AfterEach
    fun `clean todos`(@Autowired todosRepository: InMemoryTodosRepository) {
        todosRepository.clear()
    }

    @Test
    fun `get empty todos`() {
        expectTodos("user", emptyIterable())
    }

    @Test
    fun `deny access on missing authentication`() {
        mockMvc.get("/todos").andExpect { status { isForbidden() } }
        mockMvc.post("/todos").andExpect { status { isForbidden() } }
    }

    @Nested
    inner class `single saved` {

        @BeforeEach
        fun `save single todo`() {
            saveTodo(user = "user", "new todo")
        }

        @Test
        fun `get all for user`() {
            expectTodos(user = "user", contains("new todo"))
        }

        @Test
        fun `save second todo`() {
            saveTodo(user = "user", "second todo")

            expectTodos(user = "user", contains("new todo", "second todo"))
        }
    }

    @Nested
    inner class `saves by multiple users` {

        @BeforeEach
        fun `save single todo`() {
            saveTodo(user = "user1", "user1 todo")
            saveTodo(user = "user2", "user2 todo")
        }

        @ParameterizedTest
        @CsvSource(
            "user1, user1 todo",
            "user2, user2 todo",
        )
        fun `get all for user`(user: String, todo: String) {
            expectTodos(user, contains(todo))
        }
    }

    private fun expectTodos(user: String, matcher: Matcher<Iterable<String>>) =
        mockMvc.get("/todos") {
            with(user(user))
        }.andExpect {
            status { is2xxSuccessful() }
            jsonPath("\$.todos", matcher)
        }

    private fun saveTodo(user: String, name: String) =
        mockMvc.post("/todos") {
            with(user(user))
            contentType = MediaType.APPLICATION_JSON
            content = """ { "name": "$name" } """
        }.andExpect {
            status { is2xxSuccessful() }
        }
}
