package com.example.walmartchallenge.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartchallenge.R
import com.example.walmartchallenge.adapter.SearchItemsAdapter
import com.example.walmartchallenge.adapter.ViewHolderClick
import com.example.walmartchallenge.constants.Constants
import com.example.walmartchallenge.data.db.ProductSearch
import com.example.walmartchallenge.data.entities.ProductSearchEntity
import com.example.walmartchallenge.data.pojo.ItemPojo
import com.example.walmartchallenge.databinding.ActivityMainBinding
import com.example.walmartchallenge.di.components.DaggerActivityMainComponent
import com.example.walmartchallenge.di.modules.ActivityMainModule
import com.example.walmartchallenge.di.modules.RetroFitModule
import com.example.walmartchallenge.network.retrofit_endpoints.WalmartEndpoints
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.experimental.*

import kotlinx.coroutines.experimental.android.UI

class MainActivity : AppCompatActivity(), ViewHolderClick {


    private lateinit var activity_main_binding: ActivityMainBinding
    private lateinit var endpoints: WalmartEndpoints
    private lateinit var search_query: String
    private lateinit var db: ProductSearch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity_main_binding = DaggerActivityMainComponent.builder()
                .activityMainModule(ActivityMainModule(this, R.layout.activity_main))
                .retroFitModule(RetroFitModule(Constants.WALMART_WEB.ROOT_URL)).build().get_bindings()
        endpoints = DaggerActivityMainComponent.builder()
                .activityMainModule(ActivityMainModule(this, R.layout.activity_main))
                .retroFitModule(RetroFitModule(Constants.WALMART_WEB.ROOT_URL)).build().get_endpoints()
        db = ProductSearch.get_instance(applicationContext)!!
        activity_main_binding.searchButton.setOnClickListener {
            if (!activity_main_binding.searchQuery.text!!.isEmpty()) {
                (activity_main_binding.itemRecycler.adapter as SearchItemsAdapter).clear_items()
                search_query = activity_main_binding.searchQuery.text.toString()
                get_items(search_query = search_query)
            }
        }
        activity_main_binding.itemRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var start = 1
            private var mPreviousTotal: Int = 0
            private var mLoading: Boolean = true;


            @Override
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount: Int = recyclerView.childCount;
                val totalItemCount: Int = recyclerView.layoutManager?.getItemCount()!!
                val firstVisibleItem: Int = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (mLoading) {
                    if (totalItemCount > mPreviousTotal) {
                        mLoading = false;
                        mPreviousTotal = totalItemCount;
                    }
                }
                val visibleThreshold = 5
                if (!mLoading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold) && search_query != null) {
                    // End has been reached

                    onLoadMore(start);
                    start += 10

                    mLoading = true;
                }

            }


            fun onLoadMore(start: Int) { get_items(start, search_query) }

        })

    }

    fun get_items(start: Int = 1, search_query: String) {
        launch(context = CommonPool) {
            val response = endpoints.search_items(search_query,
                    start = start,
                    apiKey = Constants.WALMART_WEB.API_KEY).execute().body()?.items
            val adapter = (activity_main_binding.itemRecycler.adapter as SearchItemsAdapter)
            adapter.add_items(response!!)
            val new_item = ProductSearchEntity(
                    id = null,
                    timestamp = System.currentTimeMillis() / 1000,
                    search_query = activity_main_binding.searchQuery.text.toString(),
                    response = Gson().toJson(adapter.get_items(), object : TypeToken<List<ItemPojo>>() {}.type))
            db.product_search_dao().insert_response(new_item)
            launch(context = UI) {
                adapter.notifyDataSetChanged()
            }

        }
    }

    override fun on_view_click(position: Int) {
        startActivity(Intent(this,
                ItemDetailsActivity::class.java).apply {
            putExtras(Bundle().apply {

                putString("search_query", search_query)
                putInt("position", position)
            })
        })


    }
}
