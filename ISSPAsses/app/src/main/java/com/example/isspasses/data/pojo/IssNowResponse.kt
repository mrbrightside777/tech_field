package com.example.isspasses.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IssNowResponse (

    @SerializedName("timestamp")
    @Expose
    var timestamp: Int? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("iss_position")
    @Expose
    var issPosition: IssPosition? = null
)
