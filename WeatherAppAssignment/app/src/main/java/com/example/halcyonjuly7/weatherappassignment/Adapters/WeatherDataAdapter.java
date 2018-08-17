package com.example.halcyonjuly7.weatherappassignment.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halcyonjuly7.weatherappassignment.Constants;
import com.example.halcyonjuly7.weatherappassignment.R;
import com.example.halcyonjuly7.weatherappassignment.data.models.pojo.Weather;
import com.example.halcyonjuly7.weatherappassignment.data.models.pojo.WeatherList;
import com.example.halcyonjuly7.weatherappassignment.database.Entities.WeatherMapData;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherDataAdapter extends RecyclerView.Adapter<WeatherDataAdapter.ViewHolder> {

    private List<WeatherList> weather_items;

    public WeatherDataAdapter(List<WeatherList> weather_items) {
        this.weather_items = weather_items;
    }


    @Override
    public WeatherDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherList weather_item = weather_items.get(position);

        holder.weather_temperature.setText(String.valueOf(weather_item.getMain().getTempMax()));
        holder.weather_description.setText(weather_item.getWeather().get(0).getMain());
        Date date = new java.util.Date(weather_item.getDt() * 1000L);
        holder.weather_date.setText(new SimpleDateFormat(Constants.DATE_FORMAT).format(date));
        Picasso.get().load(Constants.Weather.IMG_ENDPOINT + weather_item.getWeather().get(0).getIcon() + ".png").into(holder.weather_icon);
    }



    @Override
    public int getItemCount() {
        return weather_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView weather_description;
        public TextView weather_temperature;
        public TextView weather_date;
        public ImageView weather_icon;


        public ViewHolder(View itemView) {
            super(itemView);
            weather_description = itemView.findViewById(R.id.weather_description);
            weather_temperature = itemView.findViewById(R.id.weather_temperature);
            weather_date = itemView.findViewById(R.id.weather_date);
            weather_icon = itemView.findViewById(R.id.weather_icon);

        }
    }
}
