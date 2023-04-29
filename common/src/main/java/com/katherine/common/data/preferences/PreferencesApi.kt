package com.katherine.common.data.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesApi {
    suspend fun saveTestFeature(isTurnedOn: Boolean)
    fun getTestFeature(): Flow<Boolean>
}