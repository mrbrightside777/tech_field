package com.example.halcyonjuly7.weatherappassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.halcyonjuly7.weatherappassignment.Adapters.WeatherDataAdapter;
import com.example.halcyonjuly7.weatherappassignment.Web.Retrofit.interfaces.OpenWeatherMap;
import com.example.halcyonjuly7.weatherappassignment.data.models.pojo.WeatherDataPojo;
import com.example.halcyonjuly7.weatherappassignment.data.models.pojo.WeatherList;
import com.example.halcyonjuly7.weatherappassignment.database.Entities.WeatherMapData;
import com.example.halcyonjuly7.weatherappassignment.database.WeatherDb;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableEmpty;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button get_weather_button;
    private EditText zipcode_entry;
    private TextView city_name;
    private WeatherDb db;
    private WeatherDataAdapter weatherDataAdapter;
    private List<WeatherList> weatherLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = DbHelper.getWeatherDb(this, "weather.db");

        weatherLists = new ArrayList<>();
        weatherDataAdapter = new WeatherDataAdapter(weatherLists);
        recyclerView = findViewById(R.id.weather_recycler);
        recyclerView.setAdapter(weatherDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        zipcode_entry = findViewById(R.id.zipcode_entry);
        city_name = findViewById(R.id.city_name);
        get_weather_button = findViewById(R.id.get_weather_btn);
        get_weather_button.setOnClickListener(view -> {

            String zip_code = zipcode_entry.getText().toString().trim();
            weatherLists.clear();
            if (!zip_code.isEmpty()) {
                io.reactivex.Observable.just(db)
                        .subscribeOn(Schedulers.io())
                        .map(db -> {
                            WeatherMapData weatherMapData = db.weatheMapDao().getWeatherFromZip(zipcode_entry.getText().toString());
                            if (weatherMapData == null)
                                return io.reactivex.Observable.empty();
                            return weatherMapData;
                        }).subscribe(weatherMapData -> {
                            if (weatherMapData instanceof ObservableEmpty) {
                                RetroFitHelper retroFitHelper = RetroFitHelper.getINSTANCE();
                                OpenWeatherMap openWeatherMap = retroFitHelper.getOpenWeatherMap();
                                openWeatherMap.getForeCast(zip_code, getResources().getString(R.string.api_key), "metric")
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(new SingleObserver<WeatherDataPojo>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onSuccess(WeatherDataPojo weatherDataPojo) {
                                                Gson gson = new Gson();
                                                WeatherMapData weatherMapData1 = new WeatherMapData();
                                                weatherMapData1.setResponse(gson.toJson(weatherDataPojo));
                                                weatherMapData1.setTimestamp(System.currentTimeMillis() / 1000);
                                                weatherMapData1.setZip_code(zip_code);
                                                db.weatheMapDao().insertResp(weatherMapData1);
                                                weatherLists.addAll(weatherDataPojo.getList());
                                                runOnUiThread(() -> {
                                                    weatherDataAdapter.notifyDataSetChanged();
                                                    city_name.setText(weatherDataPojo.getCity().getName());
                                                });
                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                                runOnUiThread(() -> {
                                                    weatherDataAdapter.notifyDataSetChanged();
                                                    Toast.makeText(MainActivity.this, "No results", Toast.LENGTH_SHORT).show();
                                                });
                                            }
                                        });

                            } else {
                                WeatherDataPojo weatherDataPojo = new Gson().fromJson(((WeatherMapData)weatherMapData).getResponse(), WeatherDataPojo.class);
                                runOnUiThread(() -> {
                                    city_name.setText(weatherDataPojo.getCity().getName());
                                    weatherLists.addAll(weatherDataPojo.getList());
                                    weatherDataAdapter.notifyDataSetChanged();
                                });

                            }
                        });
            }
        });
    }
}
