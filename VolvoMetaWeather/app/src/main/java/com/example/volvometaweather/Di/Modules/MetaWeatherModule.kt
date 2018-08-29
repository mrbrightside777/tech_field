package com.example.volvometaweather.Di.Modules

import com.example.volvometaweather.Network.RetroFit.Endpoints.MetaWeatherEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = arrayOf(RetroFitModule::class))
class MetaWeatherModule {
    @Provides
    fun get_endpoints(retrofit: Retrofit): MetaWeatherEndpoints {
        return retrofit.create(MetaWeatherEndpoints::class.java)
    }

}