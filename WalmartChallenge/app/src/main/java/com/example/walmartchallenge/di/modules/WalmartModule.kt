package com.example.walmartchallenge.di.modules

import com.example.walmartchallenge.network.retrofit_endpoints.WalmartEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [RetroFitModule::class])
class WalmartModule {
    @Provides
    fun get_endpoints(retrofit: Retrofit): WalmartEndpoints {
        return retrofit.create(WalmartEndpoints::class.java)
    }
}