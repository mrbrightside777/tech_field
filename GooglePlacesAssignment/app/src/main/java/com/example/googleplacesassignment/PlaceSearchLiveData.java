package com.example.googleplacesassignment;

import com.example.googleplacesassignment.Data.Pojo.PlacesSearchResponse;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

public class PlaceSearchLiveData {
    public final MutableLiveData<PlacesSearchResponse> places_search = new MutableLiveData<>();
    public final MutableLiveData<Map<String, Object>> current_selection = new MutableLiveData<>();
    public final MutableLiveData<Boolean> unselected = new MutableLiveData<>();
}
