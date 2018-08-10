package com.example.halcyonjuly7.assessment1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.halcyonjuly7.assessment1.Car;
import com.example.halcyonjuly7.assessment1.R;

import java.util.List;

public class CarRecyclerAdapter extends RecyclerView.Adapter<CarRecyclerAdapter.ViewHolder> {


    private List<Car> cars;

    public CarRecyclerAdapter(List<Car> cars) {this.cars = cars;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_recycler_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.car_model.setText(car.getModel());
        holder.car_type.setText(car.get_type());
        holder.car_year.setText(car.getYear());


    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView car_model;
        TextView car_type;
        TextView car_year;

        public ViewHolder(View itemView) {
            super(itemView);

            car_model = itemView.findViewById(R.id.car_model);
            car_type = itemView.findViewById(R.id.car_type);
            car_year = itemView.findViewById(R.id.car_year);
        }
    }
}
