package com.example.lastassignment.di.components

import com.example.lastassignment.di.modules.EndpoinstModule
import com.example.lastassignment.network.endpoints.NumberEndpoints
import com.example.lastassignment.view_model.NumbersViewModel
import dagger.Component

@Component(modules = [EndpoinstModule::class])
interface EndpointsComponent {
    fun inject(view_model:NumbersViewModel)
}