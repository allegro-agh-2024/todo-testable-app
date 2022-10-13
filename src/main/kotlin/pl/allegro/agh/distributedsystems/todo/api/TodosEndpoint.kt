package pl.allegro.agh.distributedsystems.todo.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodosEndpoint {

    @GetMapping(produces = ["application/json"])
    fun todosList() = TodosResponseDto(emptyList())
}

data class TodosResponseDto(val todos: List<String>)
