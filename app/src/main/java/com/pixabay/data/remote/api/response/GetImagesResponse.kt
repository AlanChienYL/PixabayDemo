package com.pixabay.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by AlanChien on 23,December,2019.
 */
@JsonClass(generateAdapter = true)
data class GetImagesResponse(
    @Json(name = "hits")
    val hits: MutableList<Hit>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "totalHits")
    val totalHits: Int
) {

    @JsonClass(generateAdapter = true)
    data class Hit(
        @Json(name = "comments")
        val comments: Int,
        @Json(name = "downloads")
        val downloads: Int,
        @Json(name = "favorites")
        val favorites: Int,
        @Json(name = "id")
        val id: Int,
        @Json(name = "imageHeight")
        val imageHeight: Int,
        @Json(name = "imageSize")
        val imageSize: Int,
        @Json(name = "imageWidth")
        val imageWidth: Int,
        @Json(name = "largeImageURL")
        val largeImageURL: String,
        @Json(name = "likes")
        val likes: Int,
        @Json(name = "pageURL")
        val pageURL: String,
        @Json(name = "previewHeight")
        val previewHeight: Int,
        @Json(name = "previewURL")
        val previewURL: String,
        @Json(name = "previewWidth")
        val previewWidth: Int,
        @Json(name = "tags")
        val tags: String,
        @Json(name = "type")
        val type: String,
        @Json(name = "user")
        val user: String,
        @Json(name = "user_id")
        val userId: Int,
        @Json(name = "userImageURL")
        val userImageURL: String,
        @Json(name = "views")
        val views: Int,
        @Json(name = "webformatHeight")
        val webformatHeight: Int,
        @Json(name = "webformatURL")
        val webformatURL: String,
        @Json(name = "webformatWidth")
        val webformatWidth: Int
    )
}