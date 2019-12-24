package com.pixabay.data.remote.api

import com.pixabay.data.remote.api.request.IMAGE_TYPE_PHOTO
import com.pixabay.data.remote.api.request.ImageType
import com.pixabay.data.remote.api.request.PAGE_SIZE
import com.pixabay.data.remote.api.response.GetImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by AlanChien on 23,December,2019.
 */
interface ApiService {

    @GET("api/")
    suspend fun getImages(
        @Query("key") key: String,
        @Query("page") page: Int,
        @Query("q") query: String? = null,
        @ImageType @Query("image_type") imageType: String = IMAGE_TYPE_PHOTO,
        @Query("per_page") pageSize: Int = PAGE_SIZE
    ): Response<GetImagesResponse>
}