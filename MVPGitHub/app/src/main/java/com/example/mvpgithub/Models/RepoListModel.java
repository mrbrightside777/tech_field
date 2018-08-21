package com.example.mvpgithub.Models;

import com.example.mvpgithub.Contracts.RepoListActivityContract;
import com.example.mvpgithub.Network.API.Github;
import com.example.mvpgithub.Network.RetroFitHelper;
import com.example.mvpgithub.Pojo.RepoListResponse;

import java.util.List;

import io.reactivex.Single;

public class RepoListModel implements RepoListActivityContract.Model {

    private Github github_endpoints;

    @Override
    public void initModel() {
        github_endpoints = RetroFitHelper.getINSTANCE().github_endpoints();
    }

    @Override
    public Single<List<RepoListResponse>> get_user_repos(String user_name) {
        return github_endpoints.get_user_repo(user_name);
    }
}
