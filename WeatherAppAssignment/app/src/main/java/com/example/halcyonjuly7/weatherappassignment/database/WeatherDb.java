package com.example.halcyonjuly7.weatherappassignment.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.halcyonjuly7.weatherappassignment.database.Dao.WeatheMapDao;
import com.example.halcyonjuly7.weatherappassignment.database.Entities.WeatherMapData;

@Database(entities = {WeatherMapData.class}, version = 3)
public abstract class WeatherDb extends RoomDatabase {
    public abstract WeatheMapDao weatheMapDao();
}
