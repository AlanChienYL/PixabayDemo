package com.pixabay.data.remote.api.request

import androidx.annotation.StringDef

/**
 * Created by AlanChien on 23,December,2019.
 */
const val PAGE_SIZE = 20
const val IMAGE_TYPE_ALL = "all"
const val IMAGE_TYPE_PHOTO = "photo"

@StringDef(
    IMAGE_TYPE_PHOTO,
    IMAGE_TYPE_ALL
)
@Retention(AnnotationRetention.SOURCE)
annotation class ImageType

