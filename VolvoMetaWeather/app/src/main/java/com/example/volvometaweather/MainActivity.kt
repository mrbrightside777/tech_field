package com.example.volvometaweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.volvometaweather.Adapters.WeatherForecastAdapter
//import androidx.lifecycle.ViewModelProviders
import com.example.volvometaweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var  main_activity_binding:ActivityMainBinding
    lateinit var weather_forecast_adapter:WeatherForecastAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main_activity_binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        main_activity_binding.mainViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java!!)
        weather_forecast_adapter = WeatherForecastAdapter(main_activity_binding!!.mainViewModel!!.search_resp, this)
        main_activity_binding.executePendingBindings()
        main_activity_binding.setLifecycleOwner(this)
        main_activity_binding.weatherRecycler.adapter = weather_forecast_adapter
        main_activity_binding.placeChoices.selectedItem.toString()
        main_activity_binding.weatherRecycler.layoutManager = LinearLayoutManager(this)
        main_activity_binding.text1.text
        main_activity_binding.executePendingBindings()

    }
}
