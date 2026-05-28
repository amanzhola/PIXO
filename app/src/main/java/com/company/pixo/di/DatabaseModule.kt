package com.company.pixo.di

import androidx.room.Room
import com.company.pixo.data.db.PixoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = PixoDatabase::class.java,
            name = "pixo_database"
        ).build()
    }

    single {
        get<PixoDatabase>().historyDao()
    }
}