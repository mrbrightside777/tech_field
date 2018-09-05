package com.example.walmartchallenge.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageEntity(

    @SerializedName("thumbnailImage")
    @Expose
    var thumbnailImage: String? = null,

    @SerializedName("mediumImage")
    @Expose
    var mediumImage: String? = null,

    @SerializedName("largeImage")
    @Expose
    var largeImage: String? = null,

    @SerializedName("entityType")
    @Expose
    var entityType: String? = null)