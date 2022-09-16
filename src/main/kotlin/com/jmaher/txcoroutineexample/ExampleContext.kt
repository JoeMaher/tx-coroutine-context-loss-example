package com.jmaher.txcoroutineexample

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

 data class ExampleContext(
    val value: String
) : AbstractCoroutineContextElement(ExampleContext) {

    companion object Key : CoroutineContext.Key<ExampleContext>

    override fun toString(): String = value
}