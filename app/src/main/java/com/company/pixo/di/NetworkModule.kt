package com.company.pixo.di

import org.koin.dsl.module
import okhttp3.OkHttpClient
import retrofit2.Retrofit

val networkModule = module {

    single {
        OkHttpClient.Builder().build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .build()
    }
}