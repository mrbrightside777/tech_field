package com.example.walmartchallenge.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.walmartchallenge.constants.Constants
import com.example.walmartchallenge.data.dao.ProductSearchDao
import com.example.walmartchallenge.data.entities.ProductSearchEntity

@Database(entities = [ProductSearchEntity::class], version = Constants.DB.VERSION)
abstract class ProductSearch:RoomDatabase() {
    abstract fun product_search_dao():ProductSearchDao

    companion object {
        private var INSTANCE: ProductSearch?=null

        fun get_instance(context: Context):ProductSearch? {
            if (INSTANCE == null)
                synchronized(ProductSearch::class) {

                    INSTANCE = Room.databaseBuilder(context,
                            ProductSearch::class.java,
                            Constants.DB.DB_NAME).build()
                }
            return INSTANCE
        }
    }

}
