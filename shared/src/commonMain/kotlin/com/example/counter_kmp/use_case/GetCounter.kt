package com.example.counter_kmp.use_case

import com.example.counter_kmp.CounterRepository
import com.example.counter_kmp.domain.Counter
import kotlinx.coroutines.flow.Flow

class GetCounter(private val repository: CounterRepository) {
    operator fun invoke(): Flow<Counter> {
        return repository.getCounterFlow()
    }
}
