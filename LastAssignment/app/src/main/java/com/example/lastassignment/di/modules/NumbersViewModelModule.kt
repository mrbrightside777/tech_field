package com.example.lastassignment.di.modules

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.example.lastassignment.view_model.NumbersViewModel
import dagger.Module
import dagger.Provides

@Module
class NumbersViewModelModule(val activity: FragmentActivity) {

    @Provides
    fun get_viewmodel():NumbersViewModel {
        return ViewModelProviders.of(activity).get(NumbersViewModel::class.java)
    }
}