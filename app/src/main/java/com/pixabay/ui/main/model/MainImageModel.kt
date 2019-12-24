package com.pixabay.ui.main.model

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.pixabay.BR
import com.pixabay.R

/**
 * Created by AlanChien on 24,December,2019.
 */
@EpoxyModelClass(layout = R.layout.item_main_image)
abstract class MainImageModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var imgURI: String

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.apply {
            setVariable(BR.imgURI, imgURI)
        }
    }
}