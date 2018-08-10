package com.example.halcyonjuly7.assessment1.fragements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.halcyonjuly7.assessment1.Car;
import com.example.halcyonjuly7.assessment1.R;
import com.example.halcyonjuly7.assessment1.adapters.CarRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    public static String FRAG_TAG = "Fragment1";
    private List<Car> car_list;
    private RecyclerView car_recycler;
    private CarRecyclerAdapter adapter;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2_layout, container, false);
        car_recycler = view.findViewById(R.id.car_recycler_list);
        car_list = new ArrayList<>();
        adapter = new CarRecyclerAdapter(car_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        car_recycler.setAdapter(adapter);
        car_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    public void add_car(Car car) {
        car_list.add(car);
        adapter.notifyDataSetChanged();
    }
}
