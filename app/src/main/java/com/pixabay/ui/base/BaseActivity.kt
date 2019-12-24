package com.pixabay.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.pixabay.views.epoxy.BaseRecyclerView
import com.pixabay.views.epoxy.EpoxyTrackerService

/**
 * Created by AlanChien on 23,December,2019.
 */
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    lateinit var binding: T
        private set

    lateinit var viewModel: V
        private set

    private lateinit var dataBinding: ViewDataBinding

    abstract fun getLayoutId(): Int

    abstract fun getViewModelInit(): V

    abstract val getBindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setContentView(dataBinding.root)
        setActionBar()
        setViewModelObserve()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        viewModel = getViewModelInit()
        binding.setVariable(getBindingVariable, viewModel)
        dataBinding = binding
    }

    open fun setActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0.toFloat()
        }
    }

    open fun setViewModelObserve() {
        observeErrMsg()
    }

    private val observeErrMsg = {
        viewModel.errorMsg.observe(this, Observer { errMsg ->
            takeIf { errMsg.isNotEmpty() }?.showError(errMsg)
        })
    }

    open fun showError(errMsg: String) {}

    fun addEpoxyVisibleTracker(brv: BaseRecyclerView) {
        lifecycle.addObserver(EpoxyTrackerService(brv))
    }
}