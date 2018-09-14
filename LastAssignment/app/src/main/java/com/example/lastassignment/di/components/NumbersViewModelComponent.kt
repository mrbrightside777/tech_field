package com.example.lastassignment.di.components

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.lastassignment.di.modules.EndpoinstModule
import com.example.lastassignment.di.modules.NumbersViewModelModule
import com.example.lastassignment.fragments.ItemFrag
import com.example.lastassignment.fragments.RecyclerFrag
import com.example.lastassignment.network.endpoints.NumberEndpoints
import com.example.lastassignment.views.MainActivity
import dagger.Component


@Component(modules = [NumbersViewModelModule::class])
interface NumbersViewModelComponent {
    fun inject(activity:MainActivity)
    fun inject(fragment: RecyclerFrag)
    fun inject(fragment: ItemFrag)
}