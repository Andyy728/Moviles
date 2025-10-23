package com.example.movilespractica1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp


@Composable
fun QuestionSelector(
    questions: List<Question>,
    onSelect: (Int) -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Seleccion de pregunta", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        )
        {
            itemsIndexed(questions) {index, _ ->
                Card(
                    onClick = {onSelect(index)},
                    modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))
                )
                {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize())
                    {
                        Text("${index +1 }")
                    }
                }
            }
        }
    }
}