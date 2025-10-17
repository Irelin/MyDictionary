package com.example.core_data.datasource

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject

class UserManager @Inject constructor(private val dataStore: DataStoreManager) {
    companion object {
        val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
        val USER_LAST_VISIT_TIME_KEY = longPreferencesKey("USER_LAST_VISIT_TIME")
    }

    fun getUserName() = dataStore.getString(USER_NAME_KEY)

    suspend fun saveUserName(name: String) {
        dataStore.setString(USER_NAME_KEY, name)
    }

    fun getLastVisitTime() = dataStore.read(USER_LAST_VISIT_TIME_KEY, 0)

    suspend fun saveLastVisitTime(time: Long) {
        dataStore.save(USER_LAST_VISIT_TIME_KEY, time)
    }
}