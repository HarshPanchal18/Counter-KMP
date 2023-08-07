import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.counter_kmp.di.injectedService
import com.example.counter_kmp.domain.Counter
import com.example.counter_kmp.presentation.CounterViewModel
import org.kodein.di.instance

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Counter Desktop app") {
        MaterialTheme { counterApp() }
    }
}

@Composable
fun counterApp() {
    val viewModel: CounterViewModel by injectedService.instance()
    val counterState = viewModel.getCounterFlow().collectAsState(initial = Counter())

    Scaffold {
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
                text = "Last action: ${counterState.value.message}",
                modifier = Modifier.padding(8.dp)
            )
            Row {
                Button(
                    onClick = { viewModel.incrementCounterLaunch() },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("+")
                }
                Button(
                    onClick = { viewModel.decrementCounterLaunch() },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("-")
                }
            }
        }
    }
}
