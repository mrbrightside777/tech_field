package com.example.lastassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.lastassignment.R
import com.example.lastassignment.databinding.RecyclerItemsBinding

class NumbersRecyclerAdapter(val data: MutableLiveData<List<Int>>,
                             val current_clicked:MutableLiveData<Int>): RecyclerView.Adapter<NumbersRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:RecyclerItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recycler_items, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = data.value?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data.value?.let {
            holder.binding.number.text = it[position].toString()
        }
    }

    inner class ViewHolder(val binding:RecyclerItemsBinding):RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                data.value?.let {
                    current_clicked.value = it[adapterPosition]
                }
            }
        }
    }
}