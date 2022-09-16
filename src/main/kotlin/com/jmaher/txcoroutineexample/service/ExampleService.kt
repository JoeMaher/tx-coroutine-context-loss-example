package com.jmaher.txcoroutineexample.service

import com.jmaher.txcoroutineexample.ExampleContext
import com.jmaher.txcoroutineexample.api.data.ExampleResult
import com.jmaher.txcoroutineexample.repository.ExampleRepository
import com.jmaher.txcoroutineexample.repository.data.ExampleEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.coroutines.coroutineContext

@Service
class ExampleService(
    private val exampleRepository: ExampleRepository
) {

    suspend fun createExample(
        value: String
    ) : ExampleResult = create(value = value)

    @Transactional
    suspend fun createExampleInTransaction(
        value: String
    ) : ExampleResult = create(value = value)

    private suspend fun create(
        value: String
    ) : ExampleResult {
        // set on entry to ExampleController
        assert(coroutineContext[ExampleContext.Key].toString() == value)
        val entity = ExampleEntity(value = value)
        return exampleRepository.save(entity)
            .let { savedEntity ->
                ExampleResult(
                    id = savedEntity.id!!,
                    value = savedEntity.value
                )
            }
    }
}