package com.example.counter_kmp.data

import com.example.counter_kmp.domain.Counter
import kotlinx.coroutines.flow.Flow

interface CounterDataSource {
    suspend fun increment()

    suspend fun decrement()

    fun getCounterFlow() : Flow<Counter>
}
