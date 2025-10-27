package com.example.movilespractica1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun QuizGame(modifier: Modifier, currentGenre: String?, currentQuestion: Int?)
{
    val context = LocalContext.current
    val genreQuestionMap = remember { QuestionRepository.loadQuestions(context) }
    val genres = genreQuestionMap.keys.toList()

    var selectedQuestionIndex by remember {mutableStateOf(currentQuestion)}
    var selectedGenre by remember {mutableStateOf(currentGenre) }



    if(selectedGenre == null)
    {
        GenreSelector(genres, onSelect = { selectedGenre = it})
    }else {
        if (selectedQuestionIndex == null) {
            QuestionSelector(genreQuestionMap[selectedGenre!!]!!, { selectedQuestionIndex = it }, selectedGenre!!)
        } else {
            selectedGenre?.let { genre ->
                selectedQuestionIndex?.let { index ->
                    genreQuestionMap[genre]?.getOrNull(index)?.let { question ->
                        QuizQuestion(question, index, genreQuestionMap[genre]!!, genre)
                    }
                } ?: QuestionSelector(
                    genreQuestionMap[genre].orEmpty(),
                    onSelect = { selectedQuestionIndex = it },
                    genre
                )
            } ?: GenreSelector(genres, onSelect = { selectedGenre = it })
        }
    }
}
