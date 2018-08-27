package com.example.googleplacesassignment.Di.Modules;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class LocationModule {

//    LocationRequest getLocationRequest() {
////        LocationRequest
//    }

    @Provides
    FusedLocationProviderClient getProviderClient(Context context) {
        return LocationServices.getFusedLocationProviderClient(context);
    }
}
