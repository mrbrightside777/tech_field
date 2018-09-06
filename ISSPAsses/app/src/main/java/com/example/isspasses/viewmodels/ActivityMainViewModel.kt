package com.example.isspasses.viewmodels

import android.location.Location
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.isspasses.data.misc.Constants
import com.example.isspasses.data.pojo.IssNowResponse
import com.example.isspasses.data.pojo.IssPosition
import com.example.isspasses.data.pojo.Response
import com.example.isspasses.di.components.DaggerActivityMainViewModelComponent
import com.example.isspasses.di.module.RetrofitModule
//import com.example.isspasses.di.components.DaggerActivityMainModelViewComponent
import com.example.isspasses.network.retrofit.IssEndpoints
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch

class ActivityMainViewModel: ViewModel() {
    var items:MutableLiveData<List<Response?>>
    var current_iss_pos: MutableLiveData<IssPosition>
    var issEndpoints:IssEndpoints
    init {
        items = MutableLiveData()
        current_iss_pos = MutableLiveData()
        issEndpoints = DaggerActivityMainViewModelComponent
                .builder()
                .retrofitModule(RetrofitModule(Constants.Web.BASE_URL))
                .build()
                .get_enpoints()
    }

    fun get_response(lat:Double, lon:Double) {
        launch(context = CommonPool) {
            val resp = issEndpoints.get_passes(lat, lon).execute()
            items.postValue(resp.body()?.response)
        }
    }

    fun observe_items(lifecycleOwner: LifecycleOwner, observer: Observer<List<Response?>>) {
        items.observe(lifecycleOwner, observer)
    }

    fun observe_iss_pos(lifecycleOwner: LifecycleOwner, observer: Observer<IssPosition>) {
        current_iss_pos.observe(lifecycleOwner, observer)
    }

    fun call_iss() {
        launch(context = CommonPool) {
            val resp = issEndpoints.iss_current().execute()
            current_iss_pos.postValue(resp.body()?.issPosition)
        }
    }



}