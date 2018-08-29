package com.example.volvometaweather;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.volvometaweather.Data.Remote.ConsolidatedWeather

import com.example.volvometaweather.Di.Components.DaggerMetaWeatherComponent
import com.example.volvometaweather.Di.Modules.RetroFitModule
import com.example.volvometaweather.Network.RetroFit.Endpoints.MetaWeatherEndpoints;
import com.google.android.gms.location.places.Place
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivityViewModel: ViewModel {
    var search_resp = MutableLiveData<List<ConsolidatedWeather>>()
    var metaWeatherEndpoints:MetaWeatherEndpoints

    constructor(): super() {
        metaWeatherEndpoints = DaggerMetaWeatherComponent
                .builder()
                .retroFitModule(RetroFitModule(Constants.METAWEATHER_BASE))
                .build()
                .get_metaweather_endpoints()
    }

    fun get_places(place: String) {
        metaWeatherEndpoints.get_place_coords(place)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMapIterable { return@flatMapIterable it }
                .filter {
                    return@filter it.title == place
                }
                .map {
                    return@map metaWeatherEndpoints.get_place_weather(it.woeid.toString())
                }
                .flatMap {
                    return@flatMap it.toObservable()
                }
                .map {
                    return@map it.consolidatedWeather
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    search_resp.value = it

                }
    }

}