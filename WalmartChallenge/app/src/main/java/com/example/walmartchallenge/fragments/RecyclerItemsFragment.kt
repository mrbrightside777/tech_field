package com.example.walmartchallenge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.walmartchallenge.R
import com.example.walmartchallenge.databinding.ItemsDetailsFragmentLayoutBinding


class RecyclerItemsFragment: Fragment() {
private lateinit var bindings:ItemsDetailsFragmentLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.items_details_fragment_layout, container, false)
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}