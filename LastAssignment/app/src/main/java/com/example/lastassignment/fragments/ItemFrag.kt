package com.example.lastassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.lastassignment.R
import com.example.lastassignment.databinding.ItemsFragBinding
import com.example.lastassignment.view_model.NumbersViewModel


class ItemFrag: Fragment() {

    lateinit var bindings: ItemsFragBinding
    lateinit var view_model: NumbersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.items_frag, container, false)
        return bindings.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view_model = ViewModelProviders.of(activity!!).get(NumbersViewModel::class.java)
        view_model.observe_lastnum(this, Observer {
            bindings.currentVal.text = it.toString()
        })

        view_model.observe_numbers(this, Observer {
            bindings.average.text = (it.sum() / it.size).toString()
        })
    }
}