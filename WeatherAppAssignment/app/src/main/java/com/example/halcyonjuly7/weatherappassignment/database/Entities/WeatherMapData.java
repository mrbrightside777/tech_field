package com.example.halcyonjuly7.weatherappassignment.database.Entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class WeatherMapData {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "zip_code")
    private String zip_code;

    @ColumnInfo(name = "timestamp")
    private double timestamp;

    @ColumnInfo(name = "response")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }


}
