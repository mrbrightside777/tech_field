package com.example.googleplacesassignment.Di.Components;


import com.example.googleplacesassignment.Data.Repository.GooglePlacesRepo;
import com.example.googleplacesassignment.Di.Modules.GooglePlacesRepoModule;
import com.example.googleplacesassignment.Di.Modules.MainActivityBindingModule;
import com.example.googleplacesassignment.Di.Scopes.MainActivityBindingScope;
import com.example.googleplacesassignment.databinding.MainActivityBinding;

import dagger.Component;
import dagger.Provides;

@MainActivityBindingScope
@Component(modules = {MainActivityBindingModule.class})
public interface MainActivityBindingComponent {
MainActivityBinding getMainActivityBinding();
}
