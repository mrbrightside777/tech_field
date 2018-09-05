package com.example.walmartchallenge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.walmartchallenge.R
import com.example.walmartchallenge.data.pojo.ItemPojo
import com.example.walmartchallenge.databinding.ItemsDetailsFragmentLayoutBinding
import com.squareup.picasso.Picasso

class ItemsDetailFragment: Fragment() {
    private lateinit var bindings:ItemsDetailsFragmentLayoutBinding

    companion object {
        fun new_instance(item: ItemPojo): ItemsDetailFragment {
            return ItemsDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("item_description", item.shortDescription)
                    putString("item_image", item.mediumImage)
                    putString("item_rating", item.customerRating)
                    putString("item_modelNumber", item.modelNumber.toString())
                    putString("item_seller_info", item.sellerInfo)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.items_details_fragment_layout, container, false)
        return bindings.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(arguments?.getString("item_image")).into(bindings.itemImage)
        bindings.itemLongDescription.text = arguments?.getString("item_description")
        bindings.itemModelNumber.text = arguments?.getString("item_modelNumber")
        bindings.itemRating.text = arguments?.getString("item_rating")
    }
}