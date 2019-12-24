package com.pixabay.base

import com.google.common.io.Resources
import com.pixabay.data.remote.api.ApiClient
import com.pixabay.data.remote.api.response.GetImagesResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.*
import org.junit.*
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

/**
 * Created by AlanChien on 23,December,2019.
 */
abstract class BaseTest : AutoCloseKoinTest() {

    val apiClient: ApiClient = mockk()

    @Before
    open fun setUp() {
        prepareModules()
    }

    private fun prepareModules() {
        val apiModule = module(override = true) {
            single { apiClient }
        }
        loadKoinModules(apiModule)
    }

    open fun getRespObject(fileName: String, clazz: Class<*>): Any? {
        val respJson = Resources.getResource(fileName).readText()
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter<GetImagesResponse>(clazz).fromJson(respJson)
    }
}