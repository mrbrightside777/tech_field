package com.example.lastassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastassignment.R
import com.example.lastassignment.adapters.NumbersRecyclerAdapter
import com.example.lastassignment.databinding.RecyclerFragBinding
import com.example.lastassignment.view_model.NumbersViewModel


class RecyclerFrag: Fragment() {
    lateinit var bindings: RecyclerFragBinding
    lateinit var view_model: NumbersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.recycler_frag, container, false)
        return bindings.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view_model = ViewModelProviders.of(activity!!).get(NumbersViewModel::class.java)
        bindings.numberRecycler.adapter = NumbersRecyclerAdapter(view_model.numbers, view_model.last_num)
        bindings.numberRecycler.layoutManager = LinearLayoutManager(activity)
        view_model.observe_numbers(this, Observer {
            (bindings.numberRecycler.adapter)?.notifyDataSetChanged()
        })


    }
}