package com.example.isspasses.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response(

    @SerializedName("duration")
    @Expose
    var duration: Int? = null,
    @SerializedName("risetime")
    @Expose
    var risetime: Int? = null
)