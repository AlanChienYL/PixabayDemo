package com.pixabay.di

import com.pixabay.data.remote.api.ApiClient
import org.koin.dsl.module

/**
 * Created by AlanChien on 23,December,2019.
 */
val apiModule = module {
    single { ApiClient() }
}