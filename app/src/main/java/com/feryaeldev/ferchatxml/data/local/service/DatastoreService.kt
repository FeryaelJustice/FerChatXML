package com.feryaeldev.ferchatxml.data.local.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.userPreferencesDatastore: DataStore<Preferences> by preferencesDataStore(
    name = "fer"
)

class DatastoreService @Inject constructor(
    private val context: Context,
) {
    companion object {
        private val USERNAME_KEY = stringPreferencesKey("username")
    }

    suspend fun saveUsername(username: String) {
        context.userPreferencesDatastore.edit { preferences ->
            preferences[USERNAME_KEY] = username
        }
    }

    fun getUsername(): Flow<String> =
        context.userPreferencesDatastore.data.map { preferences -> preferences[USERNAME_KEY] ?: "" }

    suspend fun closeSession() {
        context.userPreferencesDatastore.edit { preferences ->
            preferences.clear()
            preferences[USERNAME_KEY] = ""
        }
    }
}