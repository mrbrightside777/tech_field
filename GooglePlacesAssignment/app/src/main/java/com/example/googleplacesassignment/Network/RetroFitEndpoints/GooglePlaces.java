package com.example.googleplacesassignment.Network.RetroFitEndpoints;


import com.example.googleplacesassignment.Data.Pojo.AutoCompleteResponse;
import com.example.googleplacesassignment.Data.Pojo.PlacesSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlaces {

    @GET("/maps/api/place/autocomplete/json")
    Single<AutoCompleteResponse> autocomplete_result(@Query("input") String input,
                                                     @Query("types") String type,
                                                     @Query("location") String location,
                                                     @Query("radius") int radius,
                                                     @Query("key") String api_key);


    @GET("/maps/api/place/textsearch/json")
    Single<PlacesSearchResponse> search_place(@Query("query") String query,
                                              @Query("location") String location,
                                              @Query("radius") int radius,
                                              @Query("key") String key);


}
