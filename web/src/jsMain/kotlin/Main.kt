import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.counter_kmp.di.injectedService
import com.example.counter_kmp.domain.Counter
import com.example.counter_kmp.presentation.CounterViewModel
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
import org.kodein.di.compose.withDI
import org.kodein.di.instance

fun main() {
    renderComposable(rootElementId = "root") {
        counterApp()
    }
}

@Composable
fun counterApp() = withDI(injectedService) {
    val viewModel: CounterViewModel by injectedService.instance()
    val counterState = viewModel.getCounterFlow().collectAsState(initial = Counter())

    Div({ style { padding(1.em) } }) {

        H1 { Text(value = "Counter App") }

        A(
            href = "https://github.com/HarshPanchal18",
            attrs = {}
        ) { Text(value = "Click here") }

        Div {
            Span {
                Text("Value: ${counterState.value.value}, last action: ${counterState.value.message}")
            }
        }

        Div({ style { padding(1.em) } }) {

            Button(attrs = {
                onClick { viewModel.incrementCounterLaunch() }
            }) { Text("Increment") }

            Button(attrs = {
                onClick { viewModel.decrementCounterLaunch() }
            }) { Text("Decrement") }
        }
    }
}
