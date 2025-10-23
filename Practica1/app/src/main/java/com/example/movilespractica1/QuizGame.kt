package com.example.movilespractica1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun QuizGame()
{
    val questions = listOf(
        Question("¿Capital de Francia?", listOf("Madrid", "París", "Londres", "Roma"), "París"),
        Question("¿2 + 2?", listOf("3", "4", "5", "6"), "4"),
        Question("¿Color del cielo?", listOf("Rojo", "Azul", "Verde", "Negro"), "Azul")
    )

    var selectedQuestionIndex by remember {mutableStateOf<Int?>(null)}

    if(selectedQuestionIndex == null)
    {
        QuestionSelector(questions, {selectedQuestionIndex = it})
    }
    else
    {
        QuizQuestion(questions[selectedQuestionIndex!!])
    }
}