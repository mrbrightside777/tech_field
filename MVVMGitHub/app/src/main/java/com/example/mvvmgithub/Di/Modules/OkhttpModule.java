package com.example.mvvmgithub.Di.Modules;

import com.example.mvvmgithub.Misc.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@Module
public class OkhttpModule {


    private Map<String, String> headers;

    public OkhttpModule(Map<String, String> headers) {this.headers = headers;}


    @Provides
    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    public Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                for (Map.Entry<String, String> item:headers.entrySet())
                    builder.addHeader(item.getKey(), item.getValue());
//                builder.addHeader("Authorization", String.format("%s", Constants.WEB.ACCESS_TOKEN));
//                builder.addHeader("User-Agent", Constants.WEB.USER_AGENT);
                return chain.proceed(builder.build());
            }
        };
    }


    @Provides
    public OkHttpClient getOkHttp(HttpLoggingInterceptor httpLoggingInterceptor,
                                  Interceptor headerInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor(headerInterceptor).build();

    }

}
