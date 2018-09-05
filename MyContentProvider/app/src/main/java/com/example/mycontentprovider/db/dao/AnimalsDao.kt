package com.example.mycontentprovider.db.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.mycontentprovider.db.entities.AnimalEntity


@Dao
interface AnimalsDao {
    @RawQuery
    fun get_animals(query: SupportSQLiteQuery): Cursor

    @Insert
    fun add_animal(new_animal:AnimalEntity)
}