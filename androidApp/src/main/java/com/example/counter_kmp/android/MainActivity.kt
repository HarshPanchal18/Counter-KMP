package com.example.counter_kmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.counter_kmp.domain.Counter
import com.example.counter_kmp.presentation.CounterViewModel
import com.example.counter_kmp.di.injectedService
import org.kodein.di.compose.withDI
import org.kodein.di.instance

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CounterApp()
                }
            }
        }
    }

    @Composable
    private fun CounterApp() = withDI(injectedService) {
        val viewModel: CounterViewModel by injectedService.instance()
        val counterState = viewModel.getCounterFlow().collectAsState(initial = Counter())

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Counter App") })
            }
        ) {
            it
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Value: ${counterState.value.value}",
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Last Action: ${counterState.value.message}",
                    modifier = Modifier.padding(8.dp)
                )
                Row {
                    Button(
                        onClick = { viewModel.incrementCounterLaunch() },
                        modifier = Modifier.padding(4.dp)
                    ) { Text("+") }
                    Button(
                        onClick = { viewModel.decrementCounterLaunch() },
                        modifier = Modifier.padding(4.dp)
                    ) { Text("-") }
                }
            }
        }
    }
}
