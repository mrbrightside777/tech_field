package com.example.halcyonjuly7.weatherappassignment.Web.Retrofit.interfaces;

import com.example.halcyonjuly7.weatherappassignment.Constants;
import com.example.halcyonjuly7.weatherappassignment.data.models.pojo.WeatherDataPojo;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMap {

    @GET("data/2.5/forecast")
    Single<WeatherDataPojo> getForeCast(@Query("zip") String zip, @Query("appid") String app_id, @Query("units") String unit);
}
