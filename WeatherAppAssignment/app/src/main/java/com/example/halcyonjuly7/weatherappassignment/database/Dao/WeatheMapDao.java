package com.example.halcyonjuly7.weatherappassignment.database.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.halcyonjuly7.weatherappassignment.database.Entities.WeatherMapData;

@Dao
public interface WeatheMapDao {
    @Query("SELECT * FROM WeatherMapData WHERE zip_code = :zip_code;")
     WeatherMapData getWeatherFromZip(String zip_code);

    @Insert
    void insertResp(WeatherMapData weatherMapData);
}
