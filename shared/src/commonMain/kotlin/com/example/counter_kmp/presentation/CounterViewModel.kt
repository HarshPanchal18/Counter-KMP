package com.example.counter_kmp.presentation

import com.example.counter_kmp.use_case.DecrementCounter
import com.example.counter_kmp.use_case.GetCounter
import com.example.counter_kmp.use_case.IncrementCounter
import com.example.counter_kmp.domain.Counter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CounterViewModel(
    private val getCounter: GetCounter,
    private val incrementCounter: IncrementCounter,
    private val decrementCounter: DecrementCounter,
) {
    fun incrementCounterLaunch() {
        CoroutineScope(Dispatchers.Default).launch {
            incrementCounter()
        }
    }

    fun decrementCounterLaunch() {
        CoroutineScope(Dispatchers.Default).launch {
            decrementCounter()
        }
    }

    fun getCounterFlow(): Flow<Counter> {
        return getCounter()
    }
}
