package com.example.isspasses.di.components

import com.example.isspasses.di.module.IssModule
import com.example.isspasses.network.retrofit.IssEndpoints
import dagger.Component

@Component(modules = [IssModule::class])
interface ActivityMainViewModelComponent {
    fun get_enpoints():IssEndpoints
}