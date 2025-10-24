package com.example.movilespractica1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun QuizQuestion(
    question: Question,
    index: Int,
    questions: List<Question>,
    genre: String
){
    val questionText = question.text
    val options = question.answers
    val correctAnswer = question.correctAnswer
    val timeLimit = 10
    val lives = 3
    val correctColor = Color(0xFF4CAF50)
    val incorrectColor = Color(0xFFFF686B)
    val lifeColors = arrayOf(correctColor, correctColor, correctColor)

    var selectedOption by remember { mutableStateOf<String?>(null) }
    var showCorrectMessage by remember { mutableStateOf(false) }
    var showIncorrectMessage by remember { mutableStateOf(false) }
    var remainingTime by remember { mutableStateOf(timeLimit)}
    var remainingLives by remember {mutableStateOf(lives)}
    var currentLifeColors by remember {mutableStateOf(lifeColors)}
    var gameOver by remember {mutableStateOf(false)}
    var gameWon by remember {mutableStateOf(false)}
    var returnToMenu by remember {mutableStateOf(false)}
    var nextQuestion by remember { mutableStateOf(false) }
    var prevQuestion by remember { mutableStateOf(false) }

    if(!returnToMenu && !prevQuestion && !nextQuestion) {
        LaunchedEffect(question)
        {
            remainingTime = timeLimit
            remainingLives = lives
            gameOver = false
            while (remainingTime > 0) {
                delay(1000L)
                remainingTime--
            }

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
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
                        isSelected ->
                            if (isCorrect) correctColor
                            else incorrectColor

                        !isSelected ->
                            if (isCorrect && gameOver) correctColor
                            else Color.LightGray

                        gameWon ->
                            if (isCorrect) correctColor
                            else Color.LightGray

                        else -> Color.LightGray
                    }

                    Button(
                        onClick = {
                            if (!gameOver && !gameWon)
                                selectedOption = option

                            if (isCorrect) {
                                gameWon = true
                                showCorrectMessage = true
                            }
                            if (!isCorrect && remainingLives > 0 && !gameWon) {
                                remainingLives--
                                currentLifeColors[remainingLives] = incorrectColor
                                if (remainingLives == 0) {
                                    showIncorrectMessage = true
                                    gameOver = true
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(option)
                    }


                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = currentLifeColors[2] // color de fondo
                        ),
                        modifier = Modifier.size(80.dp)
                    ) {}
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = currentLifeColors[1] // color de fondo
                        ),
                        modifier = Modifier.size(80.dp)
                    ) {}
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = currentLifeColors[0] // color de fondo
                        ),
                        modifier = Modifier.size(80.dp)
                    ) {}
                }

                if (showCorrectMessage) {
                    Text(
                        text = "¡Correcto! Salchicha te da un besito",
                        color = correctColor,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                if (showIncorrectMessage) {
                    Text(
                        text = "¡Fallaste! La respuesta correcta era: $correctAnswer",
                        color = incorrectColor,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }


            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter) // 👈 coloca la fila abajo
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .padding(16.dp),

                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    if (index - 1 >= 0)
                        prevQuestion = true
                }) { Text("<") }
                Button(onClick = { returnToMenu = true }) { Text("\uD83C\uDFE0") }
                Button(onClick = {
                    if (index + 1 < questions.count())
                        nextQuestion = true
                }) { Text(">") }
            }
        }
    }
        else if(returnToMenu)
        {
            QuizGame(modifier= Modifier,genre, null)
        }
    else if(prevQuestion) {
        QuizGame(Modifier, genre, index - 1 )
    }
    else if(nextQuestion)
    {
        QuizGame(Modifier, genre, index + 1 )
    }

}
