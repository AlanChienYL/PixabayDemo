package com.pixabay.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.VisibilityState
import com.pixabay.data.remote.api.response.GetImagesResponse
import com.pixabay.data.remote.api.response.GetImagesResponse.Hit
import com.pixabay.data.remote.interactor.ImagesInteractor
import com.pixabay.ext.notifyObserver
import com.pixabay.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by AlanChien on 23,December,2019.
 */
const val START_PAGE = 1
const val QUERY_STR = "diving"

class MainViewModel : BaseViewModel() {

    private lateinit var requestJob: Job
    private var page = START_PAGE
    private var totalCnt: Int = 0
    private var noMorePage = false
    private val imagesResult: MutableList<Hit> = arrayListOf()
    val loadingNextPage: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _images = MutableLiveData<List<Hit>>(imagesResult)
    val images: LiveData<List<Hit>>
        get() = _images
    private val interactor = ImagesInteractor(::apiSuccess, ::apiFailure)

    fun start() {
        isLoading.value = true
        loadImages()
    }

    private fun loadImages() {
        requestJob = viewModelScope.launch {
            interactor.getImages(page, QUERY_STR)
        }
    }

    private fun apiSuccess(resp: GetImagesResponse) {
        Timber.d("in apiSuccess ${resp.hits.size}")
        hideProgress()
        setTotalCount(resp.total)
        updateImageList(resp.hits)
    }

    private fun setTotalCount(totalCntResp: Int) = takeIf { totalCnt == 0 }?.let {
        totalCnt = totalCntResp
    }

    private fun updateImageList(hits: List<Hit>) {
        imagesResult.addAll(hits)
        _images.notifyObserver()
    }

    private fun apiFailure(msg: String) {
        errorMsg.value = msg
        noMorePage = true
        hideProgress()
    }

    private fun hideProgress() {
        when (images.value?.size) {
            0 -> isLoading.value = false
            else -> loadingNextPage.value = false
        }
    }

    fun onVisibilityStateChanged(index: Int, visibilityState: Int) = takeIf {
        isFinishToGetPageImages() && isGetAllImageAlready() &&
                isLastImageVisible(index, visibilityState) && !noMorePage
    }?.loadNextPage()

    private fun isFinishToGetPageImages(): Boolean =
        ::requestJob.isInitialized && requestJob.isCompleted

    private fun isLastImageVisible(index: Int, visibilityState: Int): Boolean =
        images.value?.size == index + 1 && visibilityState == VisibilityState.FULL_IMPRESSION_VISIBLE

    private fun isGetAllImageAlready(): Boolean = images.value?.size != totalCnt

    private fun loadNextPage() {
        loadingNextPage.value = true
        page += 1
        loadImages()
    }
}