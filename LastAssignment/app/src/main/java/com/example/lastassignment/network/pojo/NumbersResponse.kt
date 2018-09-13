package com.example.lastassignment.network.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NumbersResponse (

        @SerializedName("type")
        @Expose
        var type: String? = null,

        @SerializedName("length")
        @Expose
        var length: Int? = null,

        @SerializedName("data")
        @Expose
        var data: List<Int>? = null,

        @SerializedName("success")
        @Expose
        var success: Boolean? = null
)
