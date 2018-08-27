package com.example.googleplacesassignment.Di.Modules;


import com.example.googleplacesassignment.Network.RetroFitEndpoints.GooglePlaces;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = RetroFitModule.class)
public class GooglePlacesModule {
    @Provides
    GooglePlaces getEndpoints(Retrofit retrofit) {
        return retrofit.create(GooglePlaces.class);
    }
}
