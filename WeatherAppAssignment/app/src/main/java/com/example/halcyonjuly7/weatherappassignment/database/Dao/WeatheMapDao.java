package com.example.halcyonjuly7.weatherappassignment.database.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.halcyonjuly7.weatherappassignment.database.Entities.WeatherMapData;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface WeatheMapDao {
    @Query("SELECT * FROM WeatherMapData WHERE zip_code = :zip_code;")
    Single<WeatherMapData> getWeatherFromZip(String zip_code);

    @Insert
    void insertResp(WeatherMapData weatherMapData);
}
