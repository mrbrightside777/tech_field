package com.example.volvometaweather.Di.Modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = arrayOf(OkhttpModule::class))
class RetroFitModule(private val base_url:String) {

    @Provides
    fun get_retro_fit(client:OkHttpClient):Retrofit {
        return Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }
}