package com.example.movilespractica1

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object QuestionRepository {

    fun loadQuestions(context: Context): Map<String, List<Question>> {
        return try {

            val inputStream = context.assets.open("questions.json")
            val json = inputStream.bufferedReader().use { it.readText() }

            val type = object : TypeToken<Map<String, List<Question>>>() {}.type

            Gson().fromJson<Map<String, List<Question>>>(json, type)
        } catch (e: Exception) {
            Log.e("QuestionRepository", "Error al cargar el JSON: ${e.message}")
            emptyMap()
        }
    }
}
