package pl.allegro.agh.distributedsystems.todo.api

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
        mockMvc.get("/todos").andExpect {
            status { is2xxSuccessful() }
            content { json(""" { "todos": [] } """) }
        }
    }

    @Test
    fun `get single`() {
        mockMvc.post("/todos") {
            contentType = MediaType.APPLICATION_JSON
            content = """ { "name": "new todo" } """
        }.andExpect {
            status { is2xxSuccessful() }
        }

        mockMvc.get("/todos").andExpect {
            status { is2xxSuccessful() }
            content { json(""" { "todos": ["new todo"] } """) }
        }
    }
}
