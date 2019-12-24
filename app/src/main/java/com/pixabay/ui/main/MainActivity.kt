package com.pixabay.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.pixabay.BR
import com.pixabay.R
import com.pixabay.databinding.ActivityMainBinding
import com.pixabay.ext.snack
import com.pixabay.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.activity_main_cl
import kotlinx.android.synthetic.main.activity_main.activity_main_rlv
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

/**
 * Created by AlanChien on 23,December,2019.
 */

const val SPAN_COUNT = 5

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val controller: MainController by lazy { MainController(viewModel) }

    override val getBindingVariable: Int
        get() = BR.vm

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModelInit(): MainViewModel = getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRecyclerView()
        handleImages()
        handleLoadingNextPage()
        viewModel.start()
    }

    override fun showError(errMsg: String) {
        activity_main_cl.snack(errMsg)
    }

    private fun setRecyclerView() {
        activity_main_rlv.apply {
            setController(controller)
            layoutManager = GridLayoutManager(this@MainActivity, SPAN_COUNT)
        }
        addEpoxyVisibleTracker(activity_main_rlv)
    }

    private fun handleImages() = viewModel.images.observe(this, Observer {
        Timber.d("in handleImages ${it.size}")
        controller.apply {
            hits = it
            requestModelBuild()
        }
    })

    private fun handleLoadingNextPage() = viewModel.loadingNextPage.observe(this, Observer { loading ->
        controller.apply {
            this.loading = loading
            requestModelBuild()
            scrollListToTheBottom()
        }
    })

    private fun scrollListToTheBottom() = viewModel.images.value?.size?.let { position ->
        activity_main_rlv.smoothScrollToPosition(position)
    }
}