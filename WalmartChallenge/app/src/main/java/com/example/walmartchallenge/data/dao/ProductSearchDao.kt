package com.example.walmartchallenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.walmartchallenge.data.entities.ProductSearchEntity


@Dao
interface ProductSearchDao {
    @Query("SELECT * FROM ProductSearchEntity WHERE search_query = :search_query")
    fun get_respone_from_query(search_query:String): ProductSearchEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert_response(response: ProductSearchEntity)
}