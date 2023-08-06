package com.example.counter_kmp.di

import com.example.counter_kmp.CounterRepository
import com.example.counter_kmp.presentation.CounterViewModel
import com.example.counter_kmp.use_case.DecrementCounter
import com.example.counter_kmp.use_case.GetCounter
import com.example.counter_kmp.data.InMemoryCounterDataSource
import com.example.counter_kmp.use_case.IncrementCounter
import org.kodein.di.DI
import org.kodein.di.bindProvider

val injectedService = DI {
    //data
    val counterDataSource = InMemoryCounterDataSource()
    val counterRepository = CounterRepository(counterDataSource)

    // Use cases
    val getCounter = GetCounter(counterRepository)
    val incrementCounter = IncrementCounter(counterRepository)
    val decrementCounter = DecrementCounter(counterRepository)

    // ViewModel
    bindProvider { CounterViewModel(getCounter,incrementCounter,decrementCounter) }
    // This means that the view model will now be injectable in our app view on all platforms. Neat!
}
