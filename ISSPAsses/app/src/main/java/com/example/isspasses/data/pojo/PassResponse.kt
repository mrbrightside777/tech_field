package com.example.isspasses.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PassResponse(

    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("request")
    @Expose
    var request: Request? = null,
    @SerializedName("response")
    @Expose
    var response: List<Response>? = null
)
