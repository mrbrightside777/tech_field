package com.example.googleplacesassignment;



import android.location.Location;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

public class LocationObservableFields {
    public final ObservableField<Location> locationField = new ObservableField<>();
}
