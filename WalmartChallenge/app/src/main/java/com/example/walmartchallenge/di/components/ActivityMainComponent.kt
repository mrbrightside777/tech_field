package com.example.walmartchallenge.di.components

import com.example.walmartchallenge.databinding.ActivityMainBinding
import com.example.walmartchallenge.di.modules.ActivityMainModule
import com.example.walmartchallenge.di.modules.RetroFitModule
import com.example.walmartchallenge.di.modules.WalmartModule
import com.example.walmartchallenge.network.retrofit_endpoints.WalmartEndpoints
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [ActivityMainModule::class])
interface ActivityMainComponent {
    fun get_endpoints(): WalmartEndpoints
    fun get_bindings(): ActivityMainBinding
}