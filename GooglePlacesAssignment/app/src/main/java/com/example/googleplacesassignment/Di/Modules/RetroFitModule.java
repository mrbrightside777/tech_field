package com.example.googleplacesassignment.Di.Modules;


import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkhttpModule.class)
public class RetroFitModule {

    private String base_url;

    public RetroFitModule(String base_url) {this.base_url = base_url;}

    @Provides
    Retrofit getRetroFit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

}
