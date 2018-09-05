package com.example.walmartchallenge.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module(includes = [OkHttpModule::class])
class RetroFitModule(val base_url:String) {

    @Provides
    fun get_retrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(base_url)
//            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()
    }
}