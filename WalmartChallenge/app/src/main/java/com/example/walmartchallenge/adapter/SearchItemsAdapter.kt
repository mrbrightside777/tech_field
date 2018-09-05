package com.example.walmartchallenge.adapter

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartchallenge.R
import com.example.walmartchallenge.data.db.ProductSearch
import com.example.walmartchallenge.data.entities.ProductSearchEntity
import com.example.walmartchallenge.data.pojo.ItemPojo
import com.example.walmartchallenge.databinding.SearchItemsBinding
import com.example.walmartchallenge.views.ItemDetailsActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso

class SearchItemsAdapter(val context: AppCompatActivity): RecyclerView.Adapter<SearchItemsAdapter.ViewHolder>() {

    val list_items: MutableList<ItemPojo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.search_items,
                parent,
                false))
    }

    override fun getItemCount(): Int = list_items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current_item = list_items.get(position)
        Picasso.get().load(current_item.mediumImage).into(holder.bindings.itemImage)
        holder.bindings.itemRating.text = current_item.customerRating
        holder.bindings.itemModelNumber.text = current_item.modelNumber.toString()
        holder.bindings.itemDescription.text = current_item.shortDescription
        holder.bindings.itemPrice.text = current_item.salePrice.toString()
    }

    inner class ViewHolder(val bindings:SearchItemsBinding): RecyclerView.ViewHolder(bindings.root) {
        init {
            bindings.root.setOnClickListener {
                (context as ViewHolderClick).on_view_click(adapterPosition)
            }
        }
    }

    fun add_items(items: List<ItemPojo>) {list_items.addAll(items) }

    fun get_items():MutableList<ItemPojo> = list_items
    fun clear_items() { list_items.clear() }
}

interface ViewHolderClick {
    fun on_view_click(position: Int)
}