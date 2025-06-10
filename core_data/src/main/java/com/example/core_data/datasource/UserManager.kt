package com.example.core_data.datasource

import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject

class UserManager @Inject constructor(private val dataStore: DataStoreManager) {
    companion object {
        val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
    }

    fun getUserName() = dataStore.getString(USER_NAME_KEY)

    suspend fun saveUserName(name: String) {
        dataStore.save(USER_NAME_KEY, name)
    }
}