package com.company.pixo.di

import com.company.pixo.data.adapty.AdaptyInitializer
import com.company.pixo.data.datastore.AppPreferencesDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single {
        AppPreferencesDataSource(
            context = androidContext()
        )
    }

    single {
        AdaptyInitializer(
            context = androidContext()
        )
    }
}