package com.example.walmartchallenge.data.entities

import androidx.room.*
import java.sql.Timestamp

@Entity(indices = [Index(value = "search_query", unique = true)])
data class ProductSearchEntity(
        @PrimaryKey(autoGenerate = true)
        var id:Int?,

        @ColumnInfo(name = "timestamp")
        var timestamp: Long,

        @ColumnInfo(name = "search_query")
        var search_query:String,

        @ColumnInfo(name = "response")
        var response:String
        )
