package com.romanvytv.verbis

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this

        startKoin {
            androidContext(this@MainApp)
//            modules(modules)
        }
    }
}