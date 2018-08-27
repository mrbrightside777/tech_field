package com.example.googleplacesassignment.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplacesassignment.Data.Pojo.Candidate;
import com.example.googleplacesassignment.Data.Pojo.PlacesSearchResponse;
import com.example.googleplacesassignment.Data.Pojo.Result;
import com.example.googleplacesassignment.PlaceSearchLiveData;
import com.example.googleplacesassignment.R;
import com.example.googleplacesassignment.Views.MainActivity;
import com.example.googleplacesassignment.Views.PlaceDetailActivity;
import com.example.googleplacesassignment.databinding.PlaceSearchRecyclerItemsBinding;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceSearchAdapter extends RecyclerView.Adapter<PlaceSearchAdapter.ViewHolder> {

    private List<Result> candidateList;
    private PlaceSearchLiveData placeSearchLiveData;
    private FragmentActivity lifecycleOwner;
    private Integer select_pos;



    public PlaceSearchAdapter(FragmentActivity lifecycleOwner, PlaceSearchLiveData placeSearchLiveData) {

        if (placeSearchLiveData.places_search.getValue() == null) {
            candidateList = new ArrayList<>();
        } else {
            this.candidateList = placeSearchLiveData.places_search.getValue().getResults();
        }
        this.placeSearchLiveData = placeSearchLiveData;
        this.lifecycleOwner = lifecycleOwner;
        observe_livedata(this.placeSearchLiveData);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PlaceSearchRecyclerItemsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.place_search_recycler_items, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = candidateList.get(position);
        holder.binding.placeResultAddress.setText(result.getFormattedAddress());
        holder.binding.placeResultName.setText(result.getName());
        holder.binding.placeResultRating.setText(String.valueOf(result.getRating()));
        if (select_pos != null) {
            if (position == select_pos) {
                holder.binding.getRoot().setBackgroundColor(Color.LTGRAY);
            }
            else
                holder.binding.getRoot().setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    private void observe_livedata(PlaceSearchLiveData placeSearchLiveData) {
        placeSearchLiveData.places_search.observe(lifecycleOwner, new Observer<PlacesSearchResponse>() {
            @Override
            public void onChanged(PlacesSearchResponse placesSearchResponse) {
                candidateList.clear();
                candidateList.addAll(placesSearchResponse.getResults());
                notifyDataSetChanged();
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        PlaceSearchRecyclerItemsBinding binding;

        public ViewHolder(@NonNull PlaceSearchRecyclerItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Result result = candidateList.get(getAdapterPosition());
                    LatLng latLng = new LatLng(result.getGeometry().getLocation().getLat(), result.getGeometry().getLocation().getLng());
                    Map<String, Object> map = new HashMap<>();
                    map.put("location", latLng);
                    map.put("pos", getAdapterPosition());
                    placeSearchLiveData.current_selection.setValue(map);
                    if (select_pos != null)
                        notifyItemChanged(select_pos);
                    select_pos = getAdapterPosition();
                    notifyItemChanged(select_pos);

                }
            });
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Bundle bundle = new Bundle();
                    Result result = candidateList.get(getAdapterPosition());
                    bundle.putString("place_name", result.getName());
                    bundle.putString("place_address",result.getFormattedAddress());
                    Intent intent = new Intent(lifecycleOwner, PlaceDetailActivity.class);
                    intent.putExtras(bundle);
                    lifecycleOwner.startActivity(intent);
                    return false;
                }
            });
        }
    }


}
