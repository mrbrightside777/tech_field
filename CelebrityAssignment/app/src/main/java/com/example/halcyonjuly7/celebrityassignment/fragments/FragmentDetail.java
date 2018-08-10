package com.example.halcyonjuly7.celebrityassignment.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halcyonjuly7.celebrityassignment.MainActivity;
import com.example.halcyonjuly7.celebrityassignment.R;
import com.example.halcyonjuly7.celebrityassignment.database.Celebrity;


public class FragmentDetail extends Fragment implements MainActivity.FragmentCallback {
    TextView celeb_name;
    TextView celeb_job;
    TextView celeb_description;
    ImageView celeb_image;
    public static String FRAG_TAG = "frag_detail";

    @Override
    public void callBack(Object item) {
        Celebrity celebrity = (Celebrity)item;
        celeb_name.setText(celebrity.getName());
        celeb_job.setText(celebrity.getJob_title());
        celeb_description.setText(celebrity.getDescription());
        int id = getContext().getResources().getIdentifier(celebrity.getImage().replace(".jpg", ""), "drawable", getContext().getPackageName());
        celeb_image.setImageResource(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_layout, container, false);
        celeb_name = view.findViewById(R.id.celeb_name);
        celeb_job = view.findViewById(R.id.celeb_job);
        celeb_description = view.findViewById(R.id.celeb_description);
        celeb_image = view.findViewById(R.id.celeb_image);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
