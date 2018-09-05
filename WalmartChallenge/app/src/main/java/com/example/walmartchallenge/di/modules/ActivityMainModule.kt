package com.example.walmartchallenge.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmartchallenge.R
import com.example.walmartchallenge.adapter.SearchItemsAdapter
import com.example.walmartchallenge.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides

@Module(includes = [WalmartModule::class])
class ActivityMainModule(val activity:AppCompatActivity, val layout_id:Int) {


    @Provides
    fun get_bindings(): ActivityMainBinding {
        val activity_main_binding:ActivityMainBinding = DataBindingUtil.setContentView(activity, layout_id)
        activity_main_binding.itemRecycler.adapter = SearchItemsAdapter(activity)
        activity_main_binding.itemRecycler.layoutManager = LinearLayoutManager(activity)
        return activity_main_binding
    }
}