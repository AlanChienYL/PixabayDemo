package com.pixabay.di

import android.app.Application
import io.mockk.*
import org.junit.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

/**
 * Created by AlanChien on 23,December,2019.
 */
class KoinTest : AutoCloseKoinTest() {

    @Test
    fun tesKoinComponents() {
        startKoin {
            androidContext(mockk<Application>())
            modules(listOf(apiModule, viewModel))
        }.checkModules()
    }
}