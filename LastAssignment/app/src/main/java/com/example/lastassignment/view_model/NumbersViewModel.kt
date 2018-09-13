package com.example.lastassignment.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.lastassignment.di.components.DaggerNumbersViewModelComponent
import com.example.lastassignment.di.modules.RetrofitModule
import com.example.lastassignment.misc.Constants
import com.example.lastassignment.network.endpoints.NumberEndpoints
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch

class NumbersViewModel: ViewModel() {
    val numbers:MutableLiveData<List<Int>> = MutableLiveData()
    val last_num:MutableLiveData<Int> = MutableLiveData()
    var number_endpoints:NumberEndpoints


    init {
        number_endpoints = DaggerNumbersViewModelComponent.builder()
                .retrofitModule(RetrofitModule( Constants.WEB.BASE_URL))
                .build()
                .get_endpoints()
    }


    fun observe_numbers(lifecycleOwner: LifecycleOwner, observer: Observer<List<Int>>) {
        numbers.observe(lifecycleOwner, observer)
    }
    fun observe_lastnum(lifecycleOwner: LifecycleOwner, observer: Observer<Int>) {
        last_num.observe(lifecycleOwner, observer)
    }

    fun call_endpoint(length:String="40", type:String="uint8") {
        launch(context = CommonPool) {
            val resp = number_endpoints.get_numbers(length, type).execute().body()
            resp?.let {
                numbers.postValue(it.data)
            }
        }

    }

}