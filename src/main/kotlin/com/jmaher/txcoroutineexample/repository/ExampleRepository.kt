package com.jmaher.txcoroutineexample.repository

import com.jmaher.txcoroutineexample.repository.data.ExampleEntity
import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface ExampleRepository : CoroutineSortingRepository<ExampleEntity, BigInteger> {

    suspend fun getByValue(value: String) : ExampleEntity?
}