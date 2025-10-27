package com.example.movilespractica1

data class Question(
    val text: String,
    val answers: List<String>,

    val correctAnswer: String,
    val audioFileName1: String,
    val audioFileName2: String,
    val audioFileName3: String,
    val audioFileName4: String
)
/* JSON: Practica1/app/src/main/assets/questions.json
*           IMPORTANTE última línea sin coma
* MÚSICA: Para añadir canciones hay que meterlas en app/src/main/res/raw/prueba_audio.mp3
*           SIN MAYUSCULAS en el nombre
*
* */
