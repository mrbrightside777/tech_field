package com.example.mvpgithub.Models;

import com.example.mvpgithub.Contracts.MainActivityContract;
import com.example.mvpgithub.Network.API.Github;
import com.example.mvpgithub.Network.RetroFitHelper;
import com.example.mvpgithub.Pojo.RepoListResponse;
import com.example.mvpgithub.Pojo.UserResponse;

import java.util.List;

import io.reactivex.Single;

public class GithubModel implements MainActivityContract.Model {

    private Github github_endpoints;




    @Override
    public Single<UserResponse> get_user(String user_id) {
        return github_endpoints.search_user(user_id);
    }

    @Override
    public Single<List<RepoListResponse>> get_user_repo(String user_id) {
        return github_endpoints.get_user_repo(user_id);
    }


    @Override
    public void initModel() {
        github_endpoints = RetroFitHelper.getINSTANCE().github_endpoints();
    }
}
