package com.example.googleplacesassignment.Di.Components;


import com.example.googleplacesassignment.Di.Modules.GooglePlacesModule;
import com.example.googleplacesassignment.Di.Modules.RetroFitModule;
import com.example.googleplacesassignment.Network.RetroFitEndpoints.GooglePlaces;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = GooglePlacesModule.class)
public interface GooglePlacesComponent {
    GooglePlaces getGooglePlaces();
}
