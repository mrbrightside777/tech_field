package com.example.lastassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.lastassignment.R
import com.example.lastassignment.databinding.ActivityMainBinding
import com.example.lastassignment.view_model.NumbersViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var view_model: NumbersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        view_model = ViewModelProviders.of(this).get(NumbersViewModel::class.java)
        view_model.call_endpoint()
    }
}
