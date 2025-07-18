package com.example.ui_take_home_app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // koin setup
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)

            modules(

            ) // Register your DI modules

        }


    }
}