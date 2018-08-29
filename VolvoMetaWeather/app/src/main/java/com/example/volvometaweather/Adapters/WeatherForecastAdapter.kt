package com.example.volvometaweather.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.volvometaweather.Constants
import com.example.volvometaweather.Data.Remote.ConsolidatedWeather
import com.example.volvometaweather.Data.Remote.SearchPojo
import com.example.volvometaweather.R
import com.example.volvometaweather.databinding.WeatherResultsItemsBinding
import com.squareup.picasso.Picasso


class WeatherForecastAdapter: RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    var items:MutableLiveData<List<ConsolidatedWeather>>
    var actual_items:MutableList<ConsolidatedWeather>
    var context: LifecycleOwner

    constructor(items:MutableLiveData<List<ConsolidatedWeather>>, context: LifecycleOwner):super() {
        this.items = items
        this.context  = context
        this.actual_items = mutableListOf()
        this.items.observe(context, Observer {
            actual_items.clear()
            actual_items.addAll(it)
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:WeatherResultsItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.weather_results_items, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount():Int = actual_items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current_item = items.value!![position]
        Picasso.get().load("${Constants.METAWEATHER_BASE}${String.format(Constants.WEATHER_ICON, current_item.weatherStateAbbr)}").into(holder.binding.weatherIcon)
        holder.binding.weatherName.text = current_item.weatherStateName
        holder.binding.weatherPressure.text = String.format("%.2f", current_item.airPressure)
        holder.binding.weatherWindSpeed.text =  String.format("%.2f", current_item.windSpeed)
    }

    inner class ViewHolder(val binding: WeatherResultsItemsBinding): RecyclerView.ViewHolder(binding.root)

}