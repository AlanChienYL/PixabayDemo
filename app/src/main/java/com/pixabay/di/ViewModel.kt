package com.pixabay.di

import com.pixabay.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by AlanChien on 23,December,2019.
 */
val viewModel = module {
    viewModel { MainViewModel() }
}