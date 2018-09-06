package com.example.isspasses.di.module

import com.example.isspasses.network.retrofit.IssEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class IssModule {
    @Provides
    fun get_iss_endpoints(retrofit: Retrofit): IssEndpoints {
        return retrofit.create(IssEndpoints::class.java)
    }
}