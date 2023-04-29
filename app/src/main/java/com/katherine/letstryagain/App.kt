package com.katherine.letstryagain

import android.app.Application
import com.katherine.letstryagain.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(allModules)
        }
    }
}