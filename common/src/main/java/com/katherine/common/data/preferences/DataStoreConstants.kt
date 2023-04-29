package com.katherine.common.data.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStoreConstants {
    const val APP_PREFERENCES_NAME = "app_preferences"
    val IS_TEST_FEATURE_TURNED_ON = booleanPreferencesKey("is_test_feature_turned_on")
}