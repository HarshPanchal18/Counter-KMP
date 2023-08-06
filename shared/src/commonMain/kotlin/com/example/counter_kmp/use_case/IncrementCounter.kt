package com.example.counter_kmp.use_case

import com.example.counter_kmp.CounterRepository

class IncrementCounter(private val repository: CounterRepository) {

    suspend operator fun invoke() {
        repository.increment()
    }
}
