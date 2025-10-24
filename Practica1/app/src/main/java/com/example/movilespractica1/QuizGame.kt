package com.example.movilespractica1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun QuizGame(modifier: Modifier, currentGenre: String?, currentQuestion: Int?)
{

    val popQuestions = listOf(
        Question("pop1", listOf("Madrid", "París", "Londres", "Roma"), "París"),
        Question("¿2 + 2?", listOf("3", "4", "5", "6"), "4"),
        Question("¿Color del cielo?", listOf("Rojo", "Azul", "Verde", "Negro"), "Azul")
    )

    val rockQuestions = listOf(
        Question("rcok1", listOf("Madrid", "París", "Londres", "Roma"), "París"),
        Question("¿2 + 2?", listOf("3", "4", "5", "6"), "4"),
        Question("¿Color del cielo?", listOf("Rojo", "Azul", "Verde", "Negro"), "Azul")
    )

    val reggaetonQuestions = listOf(
        Question("¿reg1", listOf("Madrid", "París", "Londres", "Roma"), "París"),
        Question("¿2 + 2?", listOf("3", "4", "5", "6"), "4"),
        Question("¿Color del cielo?", listOf("Rojo", "Azul", "Verde", "Negro"), "Azul")
    )

    val electronicQuestions = listOf(
        Question("¿el1", listOf("Madrid", "París", "Londres", "Roma"), "París"),
        Question("¿2 + 2?", listOf("3", "4", "5", "6"), "4"),
        Question("¿Color del cielo?", listOf("Rojo", "Azul", "Verde", "Negro"), "Azul")
    )

    val miscelaneousQuestions = listOf(
        Question("¿mis1", listOf("Madrid", "París", "Londres", "Roma"), "París"),
        Question("¿2 + 2?", listOf("3", "4", "5", "6"), "4"),
        Question("¿Color del cielo?", listOf("Rojo", "Azul", "Verde", "Negro"), "Azul")
    )

    val genres = listOf(
        "Pop",
        "Rock",
        "Reggateon",
        "Electrónica",
        "Miscelánea"
    )

    val genreQuestionMap:  Map<String, List<Question>> = mapOf(
    "Pop" to popQuestions,
    "Rock" to rockQuestions,
    "Reggateon" to reggaetonQuestions,
    "Electrónica" to electronicQuestions,
    "Miscelánea" to miscelaneousQuestions
    )

    var selectedQuestionIndex by remember {mutableStateOf(currentQuestion)}
    var selectedGenre by remember {mutableStateOf(currentGenre) }



    if(selectedGenre == null)
    {
        GenreSelector(genres, onSelect = { selectedGenre = it})
    }else {
        if (selectedQuestionIndex == null) {
            QuestionSelector(genreQuestionMap[selectedGenre!!]!!, { selectedQuestionIndex = it })
        } else {
            selectedGenre?.let { genre ->
                selectedQuestionIndex?.let { index ->
                    genreQuestionMap[genre]?.getOrNull(index)?.let { question ->
                        QuizQuestion(question, index, genreQuestionMap[genre]!!, genre)
                    }
                } ?: QuestionSelector(
                    genreQuestionMap[genre].orEmpty(),
                    onSelect = { selectedQuestionIndex = it }
                )
            } ?: GenreSelector(genres, onSelect = { selectedGenre = it })
        }
    }
}