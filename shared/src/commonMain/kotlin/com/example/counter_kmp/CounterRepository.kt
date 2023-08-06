package com.example.counter_kmp

import com.example.counter_kmp.data.CounterDataSource
import com.example.counter_kmp.domain.Counter
import kotlinx.coroutines.flow.Flow

class CounterRepository(private val counterDataSource: CounterDataSource) {

    suspend fun increment() {
        counterDataSource.increment()
    }

    suspend fun decrement() {
        counterDataSource.decrement()
    }

    fun getCounterFlow(): Flow<Counter> {
        return counterDataSource.getCounterFlow() // observe the counter values
    }
}
