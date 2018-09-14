package com.example.lastassignment.di.modules

import com.example.lastassignment.network.endpoints.NumberEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [RetrofitModule::class])
class EndpoinstModule {
    @Provides
    fun get_endpoints(retrofit: Retrofit):NumberEndpoints {
        return retrofit.create(NumberEndpoints::class.java)
    }

}