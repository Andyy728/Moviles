package com.example.movilespractica1

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(name = "user_progress")

object DataSaver {
        suspend fun saveProgress(context: Context, genre: String, questionNumber: Int) {
            val key = intPreferencesKey(genre)
            context.dataStore.edit { prefs ->
                prefs[key] = questionNumber
            }
        }

        fun getProgress(context: Context, genre: String) =
            context.dataStore.data.map { prefs ->
                val key = intPreferencesKey(genre)
                prefs[key] ?: 0
            }
    }
