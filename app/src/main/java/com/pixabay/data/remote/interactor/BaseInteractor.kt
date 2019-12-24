package com.pixabay.data.remote.interactor

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.pixabay.data.remote.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by AlanChien on 23,December,2019.
 */
abstract class BaseInteractor : KoinComponent, CoroutineScope {

    val apiClient: ApiClient by inject()

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        coroutineContext.cancelChildren()
    }
}