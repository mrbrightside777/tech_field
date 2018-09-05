package com.example.walmartchallenge.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class OkHttpModule {
    @Provides
    fun get_logging_interceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun get_okhttp_client(http_logger: HttpLoggingInterceptor): OkHttpClient {
        return  OkHttpClient.Builder().apply {
            addInterceptor(http_logger)
        }.build()
    }
}