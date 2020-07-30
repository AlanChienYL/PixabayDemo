package com.pixabay.ui.main

import com.airbnb.epoxy.EpoxyController
import com.pixabay.data.remote.api.response.GetImagesResponse.Hit
import com.pixabay.ui.main.model.mainImage
import com.pixabay.ui.main.model.mainLoading

/**
 * Created by AlanChien on 24,December,2019.
 */
class MainController(private val viewModel: MainViewModel) : EpoxyController() {

    lateinit var hits: List<Hit>
    var loading = false

    override fun buildModels() {
        hits.forEachIndexed { index, hit ->
            createImageModel(index, hit)
        }
        createLoadingModel()
    }

    private fun createImageModel(index: Int, hit: Hit) {
        mainImage {
            id(index)
            imgURI(hit.webformatURL)
            onVisibilityStateChanged { _, _, visibilityState ->
                viewModel.onVisibilityStateChanged(index, visibilityState)
            }
        }
    }

    private fun createLoadingModel() = takeIf { loading }?.mainLoading {
        id("loading more")
        spanSizeOverride { _, _, _ -> SPAN_COUNT }
    }
}