package com.pixabay.ext

import androidx.lifecycle.MutableLiveData

/**
 * Created by AlanChien on 24,December,2019.
 */
fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}