package com.example.isspasses.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.isspasses.adapters.PassesAdapter
import com.example.isspasses.databinding.ActivityMainBinding
import com.example.isspasses.viewmodels.ActivityMainViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityMainBindingsModule(val activity: AppCompatActivity,
                                 val layout:Int) {



    @Provides
    fun get_bindings():ActivityMainBinding {
        val bindings:ActivityMainBinding = DataBindingUtil.setContentView(activity, layout)
        bindings.passRecycler.adapter = PassesAdapter()
        bindings.passRecycler.layoutManager = LinearLayoutManager(activity)
        return bindings

    }

}