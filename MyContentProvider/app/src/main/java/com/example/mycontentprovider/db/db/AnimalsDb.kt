package com.example.mycontentprovider.db.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mycontentprovider.constants.Constants
import com.example.mycontentprovider.db.dao.AnimalsDao
import com.example.mycontentprovider.db.entities.AnimalEntity
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch

@Database(entities =[AnimalEntity::class] , version = Constants.DB.DB_VERSION)
abstract class AnimalsDb:RoomDatabase() {
    abstract fun animlasDao(): AnimalsDao

    companion object {
        private var INSTANCE: AnimalsDb? = null

        fun getInstance(context:Context): AnimalsDb? {
            if (INSTANCE == null) {
                synchronized(AnimalsDb::class) {
                    INSTANCE = Room.databaseBuilder(context,
                            AnimalsDb::class.java,
                            Constants.DB.DB_NAME).addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            launch(context = CommonPool) {
                                val new_animal = AnimalEntity(name = "dog",
                                        scientific_name = "woof_boi",
                                        description = "wassup",
                                        category = "mammal")
                                val new_animal1 = AnimalEntity(name = "cat",
                                        scientific_name = "meowk_boi",
                                        description = "wassup",
                                        category = "mammal")
                                getInstance(context)!!.animlasDao().apply {
                                    add_animal(new_animal)
                                    add_animal(new_animal1)
                                }
                            }
                        }
                    }).build()
                }
            }
            return INSTANCE
        }
    }
}