package com.example.halcyonjuly7.weatherappassignment;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.halcyonjuly7.weatherappassignment.database.WeatherDb;


public class DbHelper {

    public static WeatherDb getWeatherDb(Context context, String db_name)
    {
        return Room.databaseBuilder(context, WeatherDb.class, db_name)
                .fallbackToDestructiveMigration()
                .build();
    }
}