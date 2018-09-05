package com.example.walmartchallenge.network.retrofit_endpoints

import com.example.walmartchallenge.data.pojo.SearchPojo
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WalmartEndpoints {
    @GET("v1/search")
    fun search_items(@Query("query") search_term:String,
                     @Query("format") format:String="json",
                     @Query("start") start:Int = 1,
                     @Query("apiKey") apiKey:String): Call<SearchPojo>
}