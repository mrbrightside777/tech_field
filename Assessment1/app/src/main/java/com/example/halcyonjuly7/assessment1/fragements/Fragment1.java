package com.example.halcyonjuly7.assessment1.fragements;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.halcyonjuly7.assessment1.Car;
import com.example.halcyonjuly7.assessment1.R;

public class Fragment1 extends Fragment {

    private EditText car_model;
    private EditText car_year;
    private EditText car_type;
    private Button add_car;
    private Fragment1Listener listener;
    private Activity activity;


    public interface Fragment1Listener {
        void onAddCar(Car car);
    }

    public static String FRAG_TAG = "Fragment1";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Fragment1Listener)
            listener = (Fragment1Listener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment1_layout, container, false);

        car_model = view.findViewById(R.id.car_model);
        car_year = view.findViewById(R.id.car_year);
        car_type = view.findViewById(R.id.car_type);
        add_car = view.findViewById(R.id.add_car);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();

        add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String car_model_text = car_model.getText().toString().trim();
                String car_type_text = car_type.getText().toString().trim();
                String car_year_text = car_year.getText().toString().trim();

                if (!car_model_text.isEmpty() || !car_type_text.isEmpty() || !car_year_text.isEmpty()) {
                    listener.onAddCar(new Car(car_model.getText().toString(),
                            car_type.getText().toString(),
                            car_year.getText().toString()));

                    car_model.setText("");
                    car_type.setText("");
                    car_year.setText("");
                } else
                    Toast.makeText(activity, "Can't add empty car", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
