package com.pixabay.data.remote.api

import com.pixabay.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by AlanChien on 23,December,2019.
 */
private const val TIMEOUT_CONNECT: Long = 3
private const val TIMEOUT_READ: Long = 15

class ApiClient {

    val apiService: ApiService by lazy { provideRetrofit().create(ApiService::class.java) }

    private fun provideRetrofit() = Retrofit.Builder().apply {
        baseUrl(BuildConfig.PixabayURL)
        client(getOkHttpBuilder().build())
        addConverterFactory(MoshiConverterFactory.create())
    }.build()

    private fun getOkHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder().apply {
        addInterceptor(getLoggingLevel())
        connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
    }

    private fun getLoggingLevel(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }
}