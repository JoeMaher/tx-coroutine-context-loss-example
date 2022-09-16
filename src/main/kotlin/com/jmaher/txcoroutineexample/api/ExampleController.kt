package com.jmaher.txcoroutineexample.api

import com.jmaher.txcoroutineexample.ExampleContext
import com.jmaher.txcoroutineexample.api.data.ExampleInput
import com.jmaher.txcoroutineexample.api.data.ExampleResult
import com.jmaher.txcoroutineexample.service.ExampleService
import kotlinx.coroutines.withContext
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class ExampleController(
    private val exampleService: ExampleService
) {

    @PostMapping("/example", produces = ["application/json"])
    suspend fun createExample(
        @RequestBody input: ExampleInput
    ) : ExampleResult = withExampleContext(input.value) {
        exampleService.createExample(input.value)
    }

    @PostMapping("/example-in-transaction", produces = ["application/json"])
    suspend fun createExampleInTransaction(
        @RequestBody input: ExampleInput
    ) : ExampleResult = withExampleContext(input.value) {
        exampleService.createExampleInTransaction(input.value)
    }

    private suspend fun withExampleContext(inputValue: String, f: suspend (String) -> ExampleResult) =
        withContext(ExampleContext(inputValue)) {
            f(inputValue)
        }
}