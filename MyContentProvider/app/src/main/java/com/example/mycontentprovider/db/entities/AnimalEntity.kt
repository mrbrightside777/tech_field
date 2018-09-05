package com.example.mycontentprovider.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Animals")
data class AnimalEntity(
        @PrimaryKey(autoGenerate = true)
        var _id:Int?=null,

        @ColumnInfo(name = "category")
        var category: String,

        @ColumnInfo(name = "name")
        var name:String,

        @ColumnInfo(name= "scientific_name")
        var scientific_name:String,

        @ColumnInfo(name="description")
        var description: String,

        @ColumnInfo(name="image_url")
        var image_url:String?=null,

        @ColumnInfo(name="facts")
        var facts: String?=null
)