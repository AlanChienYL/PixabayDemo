package com.pixabay.views.binding

import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import timber.log.Timber

/**
 * Created by AlanChien on 24,December,2019.
 */
class SimpleDraweeViewBinding {

    companion object {
        @BindingAdapter("setImageURI")
        @JvmStatic
        fun bindImage(iv: SimpleDraweeView, imageURI: String) {
            Timber.d("in binding img : $imageURI")
            iv.setImageURI(imageURI)
        }
    }
}