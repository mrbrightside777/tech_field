package com.example.mvvmgithub.Data.Network.RetroFitEndpoints;


import com.example.mvvmgithub.Data.Pojo.RepoListResponse;
import com.example.mvvmgithub.Data.Pojo.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubApi {
    @GET("/search/users")
    Call<UserResponse> search_user(@Query("q") String user_id);

    @GET("users/{username}/repos")
    Call<List<RepoListResponse>> get_user_repo(@Path("username") String user_name);
}
