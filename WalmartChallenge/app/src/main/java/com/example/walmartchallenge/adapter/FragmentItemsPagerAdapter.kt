package com.example.walmartchallenge.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.walmartchallenge.data.pojo.ItemPojo
import com.example.walmartchallenge.fragments.ItemsDetailFragment

class FragmentItemsPagerAdapter(val fm:FragmentManager, val items:List<ItemPojo>): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment  = ItemsDetailFragment.new_instance(items[position])
    override fun getCount(): Int  = items.size


}