package com.example.lastassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.lastassignment.R
import com.example.lastassignment.databinding.ActivityMainBinding
import com.example.lastassignment.di.components.DaggerNumbersViewModelComponent
import com.example.lastassignment.di.modules.NumbersViewModelModule
import com.example.lastassignment.view_model.NumbersViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    @Inject
    lateinit var view_model: NumbersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        DaggerNumbersViewModelComponent.builder()
                .numbersViewModelModule(NumbersViewModelModule(this))
                .build()
                .inject(this)
        view_model.call_endpoint()
    }
}
