package com.example.googleplacesassignment.Di.Modules;

import com.example.googleplacesassignment.Data.Repository.GooglePlacesRepo;
import com.example.googleplacesassignment.Di.Scopes.MainActivityBindingScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class GooglePlacesRepoModule {
    @Provides
    GooglePlacesRepo getAutoCompleteRepo() {
        return  new GooglePlacesRepo();
    }
}
