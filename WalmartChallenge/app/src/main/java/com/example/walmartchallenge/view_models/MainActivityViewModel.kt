package com.example.walmartchallenge.view_models

import androidx.lifecycle.MutableLiveData
import com.example.walmartchallenge.data.pojo.SearchPojo

class MainActivityViewModel {
    val data:MutableLiveData<SearchPojo> = MutableLiveData()
}