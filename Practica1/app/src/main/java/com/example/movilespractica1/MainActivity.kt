package com.example.movilespractica1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movilespractica1.ui.theme.MovilesPractica1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovilesPractica1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = " ",
                        modifier = Modifier.padding(innerPadding)
                    )
                    QuizQuestion()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun QuizQuestion() {
    val question = "¿Quien es más rubia?"
    val options = listOf("Eduardo", "Er Xino", "Candelo", "Andrea")
    val correctAnswer = "Candelo"

    var selectedOption by remember { mutableStateOf<String?>(null) }
    var showMessage by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(40.dp)
    ) {
        Text(text = question, style = MaterialTheme.typography.headlineSmall)

        options.forEach { option ->
            val isCorrect = option == correctAnswer
            val isSelected = option == selectedOption

            val backgroundColor = when {
                !isSelected -> Color.LightGray
                isCorrect -> Color(0xFFAAF683) // Verde suave
                else -> Color(0xFFFF686B) // Rojo suave
            }

            Button(
                onClick = {
                    selectedOption = option
                    showMessage = isCorrect
                },
                colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(option)
            }
        }

        if (showMessage) {
            Text(
                text = "¡Correcto! Salchicha te da un besito",
                color = Color(0xFF4CAF50),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovilesPractica1Theme {
        Greeting("Android")
    }
}
