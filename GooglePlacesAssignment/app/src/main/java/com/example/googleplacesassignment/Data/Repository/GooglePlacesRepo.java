package com.example.googleplacesassignment.Data.Repository;

import android.location.Location;

import com.example.googleplacesassignment.Data.Pojo.AutoCompleteResponse;

//import com.example.googleplacesassignment.Di.Components.DaggerGooglePlacesComponent;
import com.example.googleplacesassignment.Data.Pojo.PlacesSearchResponse;
import com.example.googleplacesassignment.Di.Components.DaggerGooglePlacesComponent;
import com.example.googleplacesassignment.Di.Modules.OkhttpModule;
import com.example.googleplacesassignment.Di.Modules.RetroFitModule;
import com.example.googleplacesassignment.Misc.Constants;
import com.example.googleplacesassignment.Network.RetroFitEndpoints.GooglePlaces;
import com.example.googleplacesassignment.PlaceSearchLiveData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class GooglePlacesRepo {
    GooglePlaces retrofit;



    public GooglePlacesRepo() {
        retrofit = DaggerGooglePlacesComponent
                .builder()
                .okhttpModule(new OkhttpModule(null))
                .retroFitModule(new RetroFitModule(Constants.WEB.GOOGLE_MAPS_BASE))
                .build()
                .getGooglePlaces();
    }


    public void get_results(final MutableLiveData<AutoCompleteResponse> autoCompleteResponseMutableLiveData, final String result, final String type, final Location location, final int radius, final String api_key) {
        retrofit.autocomplete_result(result, type, String.format("%s,%s", location.getLatitude(), location.getLongitude()), radius, api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AutoCompleteResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(AutoCompleteResponse autoCompleteResponse) {
                        autoCompleteResponseMutableLiveData.setValue(autoCompleteResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public void get_place_search_results(final PlaceSearchLiveData placeSearchLiveData, final String result, final Location location, final int radius, final String api_key) {
        retrofit.search_place(result, String.format("%s,%s", location.getLatitude(), location.getLongitude()), radius, api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PlacesSearchResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(PlacesSearchResponse placesSearchResponse) {
                        placeSearchLiveData.places_search.setValue(placesSearchResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }) ;
    }




//    public MutableLiveData<AutoCompleteResponse> get_auto_complete_results() {
//        return autoCompleteResult;
//    }





}
