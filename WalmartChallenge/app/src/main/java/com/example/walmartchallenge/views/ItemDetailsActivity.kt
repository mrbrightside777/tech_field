package com.example.walmartchallenge.views

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.walmartchallenge.R
import com.example.walmartchallenge.adapter.FragmentItemsPagerAdapter
import com.example.walmartchallenge.data.db.ProductSearch
import com.example.walmartchallenge.data.pojo.ItemPojo
import com.example.walmartchallenge.databinding.ItemDetatilsActivityBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class ItemDetailsActivity: AppCompatActivity() {
    private lateinit var bindings:ItemDetatilsActivityBinding
    private lateinit var db:ProductSearch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = DataBindingUtil.setContentView(this, R.layout.item_detatils_activity)
        db = ProductSearch.get_instance(this)!!

        launch(context = CommonPool) {
           val response_entity =  db.product_search_dao().get_respone_from_query(intent.extras.getString("search_query"))
           launch(context= UI) {
               bindings.dataPager.adapter = FragmentItemsPagerAdapter(supportFragmentManager, Gson().fromJson(response_entity.response,  object : TypeToken<List<ItemPojo>>(){}.type))
               bindings.dataPager.currentItem = intent.extras.getInt("position")
           }

        }





    }
}