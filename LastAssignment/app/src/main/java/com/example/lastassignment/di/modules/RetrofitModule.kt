package com.example.lastassignment.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module(includes = [OkhttpModule::class])
class RetrofitModule(val base_url:String) {

    @Provides
    fun get_retrofit(client:OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(base_url)
            client(client)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }
}