package com.example.isspasses.network.retrofit

import com.example.isspasses.data.pojo.IssNowResponse
import com.example.isspasses.data.pojo.PassResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface IssEndpoints {
    @GET("iss-pass.json")
    fun get_passes(@Query("lat") lat: Double, @Query("lon") lon: Double): Call<PassResponse>

    @GET("iss-now.json")
    fun iss_current():Call<IssNowResponse>
}