package com.example.googleplacesassignment;

import android.location.Location;

import com.example.googleplacesassignment.Data.Repository.GooglePlacesRepo;

import androidx.lifecycle.ViewModel;

public class PlacesSearchViewModel extends ViewModel {

    private GooglePlacesRepo repo;
    private PlaceSearchLiveData placeSearchLiveData;


    public void setRepo(GooglePlacesRepo repo) {
        this.repo = repo;
    }

    public void setPlaceSearchLiveData(PlaceSearchLiveData placeSearchLiveData) {
        this.placeSearchLiveData = placeSearchLiveData;
    }


    public void get_query(String query, Location location, int radius, String app_id) {
        repo.get_place_search_results(placeSearchLiveData, query, location, radius, app_id);



//        autoCompleteResults.setValue(repo.get_auto_complete_results());
    }
}
