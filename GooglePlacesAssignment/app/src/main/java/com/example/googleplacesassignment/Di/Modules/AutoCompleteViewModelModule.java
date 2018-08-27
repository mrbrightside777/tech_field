package com.example.googleplacesassignment.Di.Modules;


import android.content.Context;

import com.example.googleplacesassignment.PlacesSearchViewModel;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class AutoCompleteViewModelModule {
    @Provides
    PlacesSearchViewModel getAutoCompleteViewModel(Context context) {
        return new PlacesSearchViewModel();
    }
}
