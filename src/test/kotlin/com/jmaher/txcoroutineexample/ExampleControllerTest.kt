package com.jmaher.txcoroutineexample

import com.jmaher.txcoroutineexample.api.data.ExampleInput
import com.jmaher.txcoroutineexample.api.data.ExampleResult
import com.jmaher.txcoroutineexample.repository.ExampleRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.web.reactive.config.EnableWebFlux
import java.util.*

@SpringBootTest
@EnableWebFlux
@AutoConfigureWebTestClient(timeout = "30m")
class ExampleControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var exampleRepository: ExampleRepository

    @Test
    fun `create example`() = runBlocking {
        create("/example")
    }

    @Test
    fun `create example in transaction`() = runBlocking {
        create("/example-in-transaction")
    }

    @Test
    fun `create example with transaction service`() = runBlocking {
        create("/example-in-transactional-operator")
    }

    private suspend fun create(uri: String) {
        val input = ExampleInput(value = UUID.randomUUID().toString())

        val result = webTestClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(input)
            .exchange()
            .expectStatus().isOk
            .expectBody<ExampleResult>()
            .returnResult().responseBody!!

        assertThat(result.value, equalTo(input.value))
        assertThat(exampleRepository.getByValue(result.value), notNullValue())
    }
}