package pl.allegro.agh.distributedsystems.todo.api

import org.hamcrest.Matcher
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.emptyIterable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class TodosEndpointTest(@Autowired private val mockMvc: MockMvc) {

    @Test
    fun `get empty todos`() {
        expectTodos(emptyIterable())
    }

    @Nested
    inner class `single saved` {

        @BeforeEach
        fun `save single todo`() {
            saveTodo("new todo")
        }

        @Test
        fun `get all`() {
            expectTodos(contains("new todo"))
        }
    }

    private fun expectTodos(matcher: Matcher<Iterable<String>>) =
        mockMvc.get("/todos").andExpect {
            status { is2xxSuccessful() }
            jsonPath("\$.todos", matcher)
        }

    private fun saveTodo(name: String) =
        mockMvc.post("/todos") {
            contentType = MediaType.APPLICATION_JSON
            content = """ { "name": "$name" } """
        }.andExpect {
            status { is2xxSuccessful() }
        }
}
