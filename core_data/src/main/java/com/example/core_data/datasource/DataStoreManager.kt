package com.example.core_data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {
    suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { it[key] = value }
    }

    fun <T> read(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        context.dataStore.data.map { it[key] ?: defaultValue }


    suspend fun setString(key: Preferences.Key<String>, value: String) = save(key, value)

    fun getString(key: Preferences.Key<String>): Flow<String> = read(key, "")
}