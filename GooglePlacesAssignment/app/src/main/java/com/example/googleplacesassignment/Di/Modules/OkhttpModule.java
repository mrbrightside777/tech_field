package com.example.googleplacesassignment.Di.Modules;


import java.io.IOException;
import java.util.Map;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class OkhttpModule {


    Map<String, String> headers;

    public OkhttpModule(Map<String, String> headers) {this.headers = headers;}


    @Provides
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    @Provides
    Interceptor getHeaderInterceptor() {
        return  new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder =  chain.request().newBuilder();
                if (headers != null) {
                    for (Map.Entry<String, String> item : headers.entrySet())
                        builder.addHeader(item.getKey(), item.getValue());
                }
                return chain.proceed(builder.build());
            }
        };
    }


    @Provides
    OkHttpClient getOkhttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Interceptor header_interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(header_interceptor)
                .build();
    }
}
