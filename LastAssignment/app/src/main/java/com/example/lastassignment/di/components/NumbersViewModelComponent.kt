package com.example.lastassignment.di.components

import com.example.lastassignment.di.modules.NumbersViewModelModule
import com.example.lastassignment.network.endpoints.NumberEndpoints
import dagger.Component


@Component(modules = [NumbersViewModelModule::class])
interface NumbersViewModelComponent {
    fun get_endpoints():NumberEndpoints
}