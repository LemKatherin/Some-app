package com.katherine.common.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.katherine.common.data.preferences.DataStoreConstants.IS_TEST_FEATURE_TURNED_ON
import kotlinx.coroutines.flow.map

class PreferencesApiImpl(private val dataStore: DataStore<Preferences>):
    PreferencesApi {
    override suspend fun saveTestFeature(isTurnedOn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_TEST_FEATURE_TURNED_ON] = isTurnedOn
        }
    }

    override fun getTestFeature() = dataStore.data.map { preferences ->
        preferences[IS_TEST_FEATURE_TURNED_ON] ?: false
    }
}