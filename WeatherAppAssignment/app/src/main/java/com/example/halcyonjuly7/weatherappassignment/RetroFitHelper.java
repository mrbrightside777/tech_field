package com.example.halcyonjuly7.weatherappassignment;

import com.example.halcyonjuly7.weatherappassignment.Web.Retrofit.interfaces.OpenWeatherMap;
import com.example.halcyonjuly7.weatherappassignment.data.models.pojo.WeatherDataPojo;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitHelper {
    private static RetroFitHelper INSTANCE;
    private OkHttpClient okHttpClient;

    public static RetroFitHelper getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new RetroFitHelper();
        return INSTANCE;
    }


    public RetroFitHelper() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }


    public OpenWeatherMap getOpenWeatherMap() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Weather.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        OpenWeatherMap openWeatherMap = retrofit.create(OpenWeatherMap.class);
        return openWeatherMap;

    }




}
