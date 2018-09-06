package com.example.isspasses.di.components

import com.example.isspasses.databinding.ActivityMainBinding
import com.example.isspasses.di.module.ActivityMainBindingsModule
import dagger.Component

@Component(modules = [ActivityMainBindingsModule::class])
interface ActivityMainComponent {
    fun get_bindings(): ActivityMainBinding
}