package com.example.googleplacesassignment.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.googleplacesassignment.AutoCompleteObservableFields;
import com.example.googleplacesassignment.Data.Pojo.AutoCompleteResponse;
import com.example.googleplacesassignment.Data.Pojo.Prediction;
import com.example.googleplacesassignment.Data.Repository.GooglePlacesRepo;

import com.example.googleplacesassignment.LocationObservableFields;
import com.example.googleplacesassignment.Misc.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class AutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

    private List<String> data;
    private AutoCompleteObservableFields autoCompleteObservabelFields;
    private GooglePlacesRepo googlePlacesRepo;
    private LocationObservableFields locationObservableFields;
    MutableLiveData<AutoCompleteResponse> autoCompleteResult = new MutableLiveData<>();


    public AutoCompleteAdapter(@NonNull Context context,
                               int resource,
                               AutoCompleteObservableFields autoCompleteObservabelFields,
                               @Named("location_fields") LocationObservableFields locationObservableFields,
                               GooglePlacesRepo repo,
                               AppCompatActivity _ctx) {
        super(context, resource);
        this.autoCompleteObservabelFields = autoCompleteObservabelFields;
        this.googlePlacesRepo = repo;
        this.locationObservableFields = locationObservableFields;
        this.data = new ArrayList<>();
        autoCompleteResult.observe(_ctx, new Observer<AutoCompleteResponse>() {
            @Override
            public void onChanged(AutoCompleteResponse autoCompleteResponse) {
                data.clear();
                List<String> items = new ArrayList<>();
                for (Prediction prediction: autoCompleteResponse.getPredictions()) {
                    items.add(prediction.getDescription());
                }
                data.addAll(items);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                googlePlacesRepo.get_results(autoCompleteResult, autoCompleteObservabelFields.current_query.get(),
                        autoCompleteObservabelFields.current_category.get(),
                        locationObservableFields.locationField.get(),
                        Constants.WEB.LOCATION_SEARCH_RADIUS,
                        Constants.WEB.GOOGLE_API_KEY);
//                AutoCompleteResponse response = googlePlacesRepo.get_auto_complete_results().getValue();
//                List<String> items = new ArrayList<>();
//                for (Prediction prediction: response.getPredictions()) {
//                    items.add(prediction.getDescription());
//                }
//                data = items;
//                filterResults.values = items;
//                filterResults.count = items.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0)
                {
                    notifyDataSetChanged();
                }
                else notifyDataSetInvalidated();
            }
        };
    }
}
