package com.example.movilespractica1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun QuizQuestion(question: Question){
    val questionText = question.text
    val options = question.answers
    val correctAnswer = question.correctAnswer
    val timeLimit = 10

    var selectedOption by remember { mutableStateOf<String?>(null) }
    var showMessage by remember { mutableStateOf(false) }
    var remainingTime by remember { mutableStateOf(timeLimit)}

    LaunchedEffect(question)
    {
        remainingTime = timeLimit
        while(remainingTime > 0)
        {
            delay(1000L)
            remainingTime--
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(40.dp)
    ) {
        Text(text = questionText, style = MaterialTheme.typography.headlineSmall)
        Text(text = "$remainingTime", style = MaterialTheme.typography.headlineSmall)

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
