package com.example.isspasses.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IssPosition (

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null,
    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

)
