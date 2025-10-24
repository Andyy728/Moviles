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
fun GenreSelector(
    genres: List<String>,
    onSelect: (String) -> Unit
)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Seleccion de género", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(24.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            )
            {
                itemsIndexed(genres) { index, genre ->
                    Card(
                        onClick = { onSelect(genre) },
                        modifier = Modifier
                            .aspectRatio(2.5f)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF000000))
                    )
                    {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize())
                        {
                            Text(genre)
                        }
                    }
                }
            }
        }
    }
}