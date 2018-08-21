package com.example.mvpgithub.Network;

import com.example.mvpgithub.Misc.Constants;
import com.example.mvpgithub.Network.API.Github;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitHelper {

    private OkHttpClient client;
    private static RetroFitHelper INSTANCE;


    public static RetroFitHelper getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new RetroFitHelper();
        return INSTANCE;
    }


    public RetroFitHelper() {
//        client = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Authorization", String.format("%s", Constants.WEB.ACCESS_TOKEN));
            builder.addHeader("User-Agent", Constants.WEB.USER_AGENT);
            return chain.proceed(builder.build());
        }).addInterceptor(interceptor).build();

    }


    public Github github_endpoints() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.WEB.GITHUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        Github github = retrofit.create(Github.class);
        return github;
    }

}
