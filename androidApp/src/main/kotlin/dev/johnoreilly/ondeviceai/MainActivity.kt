package dev.johnoreilly.ondeviceai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dev.johnoreilly.ondeviceai.ui.App
import dev.johnoreilly.ondeviceai.ui.promptApi
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val promptApiAndroid = PromptApiAndroid()

        // TODO update to check first if already downloaded
        lifecycleScope.launch { promptApiAndroid.downloadModel() }
        promptApi = promptApiAndroid

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}