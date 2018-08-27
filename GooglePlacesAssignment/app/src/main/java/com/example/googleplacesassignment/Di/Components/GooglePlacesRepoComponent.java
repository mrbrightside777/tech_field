package com.example.googleplacesassignment.Di.Components;

import com.example.googleplacesassignment.Data.Repository.GooglePlacesRepo;
import com.example.googleplacesassignment.Di.Modules.GooglePlacesRepoModule;
import com.example.googleplacesassignment.Di.Scopes.GooglePlacesRepoScope;
import com.example.googleplacesassignment.Network.RetroFitEndpoints.GooglePlaces;

import dagger.Component;
import dagger.Provides;

@GooglePlacesRepoScope
@Component(modules = GooglePlacesRepoModule.class)
public interface GooglePlacesRepoComponent {

    GooglePlacesRepo getRepo();
}
