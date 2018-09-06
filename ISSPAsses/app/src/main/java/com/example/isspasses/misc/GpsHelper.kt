package com.example.isspasses.misc

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener


class GpsHelper(val context: Context) {
    val fusedClient:FusedLocationProviderClient

    init {
        fusedClient = LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    fun last_known_loc(successListener: OnSuccessListener<Location>) {
        fusedClient.lastLocation.addOnSuccessListener(successListener)
    }
}