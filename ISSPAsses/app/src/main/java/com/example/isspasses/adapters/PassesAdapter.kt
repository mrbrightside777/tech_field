package com.example.isspasses.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.isspasses.R
import com.example.isspasses.data.pojo.Response
import com.example.isspasses.databinding.ActivityMainBinding
import com.example.isspasses.databinding.PassRecyclerBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class PassesAdapter: RecyclerView.Adapter<PassesAdapter.ViewHolder>(), ClosedAdapter<List<Response?>> {
    override fun notify_change() {
        notifyDataSetChanged()
    }

    val response_list:MutableList<Response?>

    init {
        response_list = mutableListOf()
    }


    inner class ViewHolder(val binding: PassRecyclerBinding): RecyclerView.ViewHolder(binding.root)

//    fun add_items(list: List<Response>) { response_list.addAll(list) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:PassRecyclerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.pass_recycler, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = response_list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current_item = response_list[position]
        val converted_date = Date(current_item!!.risetime!! * 1000L)

        holder.binding.duration.text = current_item!!.duration.toString()
        holder.binding.date.text = SimpleDateFormat("MM/dd/yy").format( converted_date)
//        holder.binding.risetime.text = current_item.risetime.toString()
        holder.binding.time.text = SimpleDateFormat("hh:mm").format(converted_date)
    }
    override fun add_items(item: List<Response?>) { response_list.addAll(item) }

    override fun clear_items() { response_list.clear() }

}

interface ClosedAdapter<T> {
    fun add_items(item:T)
    fun clear_items()
    fun notify_change()
}