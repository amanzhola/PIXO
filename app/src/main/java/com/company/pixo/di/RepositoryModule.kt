package com.company.pixo.di

import com.company.pixo.data.image.AndroidImageCompressor
import com.company.pixo.data.image.ImageCompressor
import com.company.pixo.data.repository.AndroidMediaRepository
import com.company.pixo.data.repository.BackendGenerationRepository
import com.company.pixo.data.repository.DataStoreTokenRepository
import com.company.pixo.data.repository.MockSubscriptionRepository
import com.company.pixo.data.repository.PlayRateAppRepository
import com.company.pixo.data.repository.RoomHistoryRepository
import com.company.pixo.data.url.BackendUrlResolver
import com.company.pixo.domain.repository.GenerationRepository
import com.company.pixo.domain.repository.HistoryRepository
import com.company.pixo.domain.repository.MediaRepository
import com.company.pixo.domain.repository.RateAppRepository
import com.company.pixo.domain.repository.SubscriptionRepository
import com.company.pixo.domain.repository.TokenRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    single {
        AndroidImageCompressor(
            context = androidContext()
        )
    }

    single<ImageCompressor> {
        get<AndroidImageCompressor>()
    }

    single {
        BackendUrlResolver(
            backendBaseUrl = pixoBackendBaseUrl()
        )
    }

    single<GenerationRepository> {
        BackendGenerationRepository(
            api = get(),
            historyDao = get(),
            imageCompressor = get(),
            urlResolver = get(),
            json = get()
        )
    }

    single<SubscriptionRepository> {
        MockSubscriptionRepository()
    }

    single<TokenRepository> {
        DataStoreTokenRepository(
            preferences = get()
        )
    }

    single<RateAppRepository> {
        PlayRateAppRepository()
    }

    single<MediaRepository> {
        AndroidMediaRepository(
            context = androidContext()
        )
    }

    single<HistoryRepository> {
        RoomHistoryRepository(
            historyDao = get()
        )
    }
}