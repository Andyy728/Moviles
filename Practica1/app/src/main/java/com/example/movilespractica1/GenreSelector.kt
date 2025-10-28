package com.example.movilespractica1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
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
    // Mapa de género -> imagen
    val genreImages = mapOf(
        "Rock" to R.drawable.logo_rock,
        "Pop" to R.drawable.logo_pop,
        "Electrónica" to R.drawable.logo_electronica,
        "Reggaeton" to R.drawable.logo_reggaeton,
        "Variado" to R.drawable.logo_variado
    )
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFafc6fe))
                    )
                    {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize())
                        {
                            val imageRes = genreImages[genre]
                            if (imageRes != null){
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = genre,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            else{
                                Text(genre)
                            }
                        }
                    }
                }
            }
        }
    }
}