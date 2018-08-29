package com.example.volvometaweather.Network.RetroFit.Endpoints

import com.example.volvometaweather.Data.Remote.LocationWeather
import com.example.volvometaweather.Data.Remote.SearchPojo
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MetaWeatherEndpoints {
    @GET("/api/location/search")
    fun get_place_coords(@Query("query") query:String): Observable<List<SearchPojo>>

    @GET("/api/location/{woeid}")
    fun get_place_weather(@Path("woeid") woeid:String): Single<LocationWeather>
}