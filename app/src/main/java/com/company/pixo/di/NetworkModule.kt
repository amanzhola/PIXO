package com.company.pixo.di

import android.os.Build
import com.company.pixo.data.remote.PixoBackendApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

private const val BACKEND_PORT = 8080
private const val PHYSICAL_PHONE_HOST = "192.168.0.177"

private fun isAndroidEmulator(): Boolean {
    return Build.FINGERPRINT.contains("generic", ignoreCase = true) ||
            Build.FINGERPRINT.contains("emulator", ignoreCase = true) ||
            Build.MODEL.contains("Emulator", ignoreCase = true) ||
            Build.MODEL.contains("Android SDK built for", ignoreCase = true) ||
            Build.MANUFACTURER.contains("Genymotion", ignoreCase = true) ||
            Build.HARDWARE.contains("goldfish", ignoreCase = true) ||
            Build.HARDWARE.contains("ranchu", ignoreCase = true)
}

fun pixoBackendBaseUrl(): String {
    return if (isAndroidEmulator()) {
        "http://10.0.2.2:$BACKEND_PORT/"
    } else {
        "http://$PHYSICAL_PHONE_HOST:$BACKEND_PORT/"
    }
}

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl(pixoBackendBaseUrl())
            .client(get())
            .addConverterFactory(
                get<Json>().asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
    }

    single {
        get<Retrofit>().create(PixoBackendApi::class.java)
    }
}
