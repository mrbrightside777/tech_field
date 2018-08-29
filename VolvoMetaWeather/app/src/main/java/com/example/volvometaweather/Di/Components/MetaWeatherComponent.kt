package com.example.volvometaweather.Di.Components

import com.example.volvometaweather.Di.Modules.MetaWeatherModule
import com.example.volvometaweather.Network.RetroFit.Endpoints.MetaWeatherEndpoints
import dagger.Component

@Component(modules = arrayOf(MetaWeatherModule::class))
interface MetaWeatherComponent {
    fun get_metaweather_endpoints():MetaWeatherEndpoints
}