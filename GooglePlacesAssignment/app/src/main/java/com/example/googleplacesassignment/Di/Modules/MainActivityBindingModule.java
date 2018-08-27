package com.example.googleplacesassignment.Di.Modules;

import com.example.googleplacesassignment.Adapters.AutoCompleteAdapter;
import com.example.googleplacesassignment.Adapters.PlaceSearchAdapter;
import com.example.googleplacesassignment.AutoCompleteObservableFields;
import com.example.googleplacesassignment.Data.Repository.GooglePlacesRepo;
import com.example.googleplacesassignment.Di.Scopes.MainActivityBindingScope;
import com.example.googleplacesassignment.LocationObservableFields;
import com.example.googleplacesassignment.PlaceSearchLiveData;
import com.example.googleplacesassignment.PlacesSearchViewModel;
import com.example.googleplacesassignment.databinding.MainActivityBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;

@Module(includes =  GooglePlacesRepoModule.class)
public class MainActivityBindingModule {

    private AppCompatActivity _activity;
    private int layout_id;

    public MainActivityBindingModule(AppCompatActivity activity, int layout_id) {
        this._activity = activity;
        this.layout_id = layout_id;
    }

    @Provides
    @MainActivityBindingScope
    PlacesSearchViewModel getSearchViewModel(GooglePlacesRepo repo, PlaceSearchLiveData placeSearchObservableFields) {
        PlacesSearchViewModel placesSearchViewModel =  ViewModelProviders.of(_activity).get(PlacesSearchViewModel.class);
        placesSearchViewModel.setRepo(repo);
        placesSearchViewModel.setPlaceSearchLiveData(placeSearchObservableFields);
        return placesSearchViewModel;
    }

    @Provides
    @MainActivityBindingScope
    LocationObservableFields getLocationObservableFields() {
        return new LocationObservableFields();
    }

    @Provides
    @MainActivityBindingScope
    AutoCompleteObservableFields geAutoCompleteObservableFields() {
        return new AutoCompleteObservableFields();
    }

    @Provides
    @MainActivityBindingScope
    AutoCompleteAdapter getAutoCompleteAdapter(AutoCompleteObservableFields autoCompleteObservabelFields,
                                               LocationObservableFields locationObservableFields,
                                               GooglePlacesRepo repo) {
        return new AutoCompleteAdapter(_activity,
                android.R.layout.simple_list_item_1,
                autoCompleteObservabelFields,
                locationObservableFields,
                repo,
                _activity
        );
    }

    @Provides
    @MainActivityBindingScope
    PlaceSearchLiveData getSearchObservableFields() {
        return new PlaceSearchLiveData();
    }

    @Provides
    @MainActivityBindingScope
    PlaceSearchAdapter getPlacesSearchAdapter(PlaceSearchLiveData searchObservableFields) {
        return new PlaceSearchAdapter(_activity, searchObservableFields);
    }

    @Provides
    @MainActivityBindingScope
    LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(_activity);
    }



    @Provides
    @MainActivityBindingScope
    MainActivityBinding getMainActivityBinding(LocationObservableFields locationObservableFields,
                                               AutoCompleteObservableFields autoCompleteObservableFields,
                                               PlacesSearchViewModel placesSearchViewModel,
                                               AutoCompleteAdapter autoCompleteAdapter,
                                               PlaceSearchAdapter placeSearchAdapter,
                                               PlaceSearchLiveData placeSearchLiveData,
                                               LinearLayoutManager linearLayoutManager) {
        MainActivityBinding mainActivityBinding = DataBindingUtil.setContentView(_activity, layout_id);
        mainActivityBinding.setLocationObservableFields(locationObservableFields);
        mainActivityBinding.setAutoCompleteObserverFields(autoCompleteObservableFields);
        mainActivityBinding.setPlaceSearchViewModel(placesSearchViewModel);
        mainActivityBinding.placeQuery.setAdapter(autoCompleteAdapter);
        mainActivityBinding.placesRecycler.setAdapter(placeSearchAdapter);
        mainActivityBinding.placesRecycler.setLayoutManager(linearLayoutManager);
        mainActivityBinding.placesRecycler.setItemAnimator(new DefaultItemAnimator());
        mainActivityBinding.setPlaceSearchLiveData(placeSearchLiveData);
        mainActivityBinding.setLifecycleOwner(_activity);
        return mainActivityBinding;

    }
}
