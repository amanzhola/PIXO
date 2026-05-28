package com.company.pixo

import android.app.Application
import android.util.Log
import com.company.pixo.data.adapty.AdaptyInitializer
import com.company.pixo.di.appModule
import com.company.pixo.di.databaseModule
import com.company.pixo.di.networkModule
import com.company.pixo.di.repositoryModule
import com.company.pixo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class PixoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setupGlobalCrashHandler()

        startKoin {
            androidContext(this@PixoApp)
            modules(
                appModule,
                databaseModule,
                repositoryModule,
                viewModelModule,
                networkModule
            )
        }

        GlobalContext.get().get<AdaptyInitializer>().initialize()
    }

    private fun setupGlobalCrashHandler() {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("PixoApp", "Uncaught exception in thread: ${thread.name}", throwable)

            defaultHandler?.uncaughtException(thread, throwable)
        }
    }
}