package com.pixabay.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by AlanChien on 23,December,2019.
 */
abstract class BaseViewModel : ViewModel() {

    val errorMsg: MutableLiveData<String> by lazy { MutableLiveData("") }
    val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData(false) }
}