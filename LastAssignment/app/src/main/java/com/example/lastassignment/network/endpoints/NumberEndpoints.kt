package com.example.lastassignment.network.endpoints


import com.example.lastassignment.network.pojo.NumbersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NumberEndpoints {

    @GET("API/jsonI.php")
    fun get_numbers(@Query("length") length:String, @Query("type") type:String): Call<NumbersResponse>
}