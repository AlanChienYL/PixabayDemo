package com.pixabay.data.remote.interactor

import com.pixabay.BuildConfig
import com.pixabay.data.remote.api.response.GetImagesResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by AlanChien on 23,December,2019.
 */
class ImagesInteractor(
    val successHandler: (GetImagesResponse) -> Unit,
    val failureHandler: (String) -> Unit
) : BaseInteractor() {

    fun getImages(page: Int, query: String? = null): Job = launch {
        try {
            val result = apiClient.apiService.getImages(
                page = page,
                query = query,
                key = BuildConfig.PixabayKey
            )
            when (result.isSuccessful) {
                true -> result.body()?.let { resp ->
                    successHandler(resp)
                }
                else -> try {
                    result.errorBody()?.string()?.let { failureHandler(it) }
                } catch (e: Exception) {
                    e.localizedMessage?.let {
                        failureHandler(it)
                    }
                }
            }
        } catch (t: Throwable) {
            Timber.e(t, "in getImages err")
            t.localizedMessage?.let { msg ->
                failureHandler(msg)
            }
        }
    }
}