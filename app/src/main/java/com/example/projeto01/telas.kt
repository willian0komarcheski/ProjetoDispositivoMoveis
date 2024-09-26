import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projeto01.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val advice by viewModel.advice
    val uselessFact by viewModel.uselessFact
    val dogFact by viewModel.dogFact

    Column {
        Button(onClick = { viewModel.fetchAdvice() }) {
            Text("Get Advice")
        }
        Text(text = "Advice: $advice")

        Button(onClick = { viewModel.fetchUselessFact() }) {
            Text("Get Useless Fact")
        }
        Text(text = "Useless Fact: $uselessFact")

        Button(onClick = { viewModel.fetchDogFact() }) {
            Text("Get Dog Fact")
        }
        Text(text = "Dog Fact: $dogFact")
    }
}
