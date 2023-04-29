package com.katherine.letstryagain.di.app

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.katherine.common.data.preferences.PreferencesApi
import com.katherine.common.data.preferences.PreferencesApiImpl
import com.katherine.common.utils.datastore.DataStoreUtils.createDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PreferencesModule {

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        createDataStore(appContext)

    @Singleton
    @Provides
    fun providePreferencesApi(dataStore: DataStore<Preferences>): PreferencesApi =
        PreferencesApiImpl(dataStore)
}