package com.jmaher.txcoroutineexample.repository.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table("example")
data class ExampleEntity(
    @Id
    val id: BigInteger? = null,
    val value: String
)