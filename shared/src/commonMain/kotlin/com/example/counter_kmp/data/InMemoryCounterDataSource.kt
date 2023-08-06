package com.example.counter_kmp.data

import com.example.counter_kmp.data.CounterDataSource
import com.example.counter_kmp.domain.Counter
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class InMemoryCounterDataSource(
    private var counter: Counter = Counter(),
    private var counterFlow: MutableSharedFlow<Counter> = MutableSharedFlow(
        extraBufferCapacity = 2,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
) : CounterDataSource {
    override suspend fun increment() {
        counter = counter.copy(value = counter.value + 1, message = "increment")
        counterFlow.tryEmit(counter)
    }

    override suspend fun decrement() {
        counter = counter.copy(value = counter.value - 1, message = "decrement")
        counterFlow.tryEmit(counter)
    }

    override fun getCounterFlow(): Flow<Counter> {
        return counterFlow.asSharedFlow()
    }
}
