package com.example.googleplacesassignment.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;

import com.example.googleplacesassignment.R;
import com.example.googleplacesassignment.databinding.ActivityPlaceDetailBinding;

public class PlaceDetailActivity extends AppCompatActivity {

    ActivityPlaceDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_place_detail);
        binding.placeDetailName.setText(getIntent().getExtras().getString("place_name"));
        binding.placeDetailAddress.setText(getIntent().getExtras().getString("place_address"));

    }
}
