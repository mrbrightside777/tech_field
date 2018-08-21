package com.example.mvpgithub.Network.API;

import com.example.mvpgithub.Pojo.RepoListResponse;
import com.example.mvpgithub.Pojo.UserResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Github {
    @GET("")
    String authenticate(String access_token);


    @GET("/search/users")
    Single<UserResponse> search_user(@Query("q") String user_id);

    @GET("users/{username}/repos")
    Single<List<RepoListResponse>> get_user_repo(@Path("username") String user_name);

}
