package com.example.lastassignment.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.lastassignment.di.components.DaggerEndpointsComponent
import com.example.lastassignment.di.components.DaggerNumbersViewModelComponent
import com.example.lastassignment.di.modules.RetrofitModule
import com.example.lastassignment.misc.Constants
import com.example.lastassignment.network.endpoints.NumberEndpoints
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class NumbersViewModel: ViewModel() {
    val numbers:MutableLiveData<List<Int>> = MutableLiveData()
    val last_num:MutableLiveData<Int> = MutableLiveData()
    @Inject
    lateinit var number_endpoints:NumberEndpoints


    init {
        DaggerEndpointsComponent.builder()
                .retrofitModule(RetrofitModule( Constants.WEB.BASE_URL))
                .build()
                .inject(this)
    }


    fun observe_numbers(lifecycleOwner: LifecycleOwner, observer: Observer<List<Int>>) {
        numbers.observe(lifecycleOwner, observer)
    }
    fun observe_lastnum(lifecycleOwner: LifecycleOwner, observer: Observer<Int>) {
        last_num.observe(lifecycleOwner, observer)
    }

    fun call_endpoint(length:String="40", type:String="uint8") {
        launch(context = Dispatchers.Default) {
            val resp = number_endpoints.get_numbers(length, type).await()
            resp?.let {
                numbers.postValue(it.data)
            }
        }

    }

}