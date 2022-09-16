package com.jmaher.txcoroutineexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TxCoroutineExampleApplication

fun main(args: Array<String>) {
    runApplication<TxCoroutineExampleApplication>(*args)
}
