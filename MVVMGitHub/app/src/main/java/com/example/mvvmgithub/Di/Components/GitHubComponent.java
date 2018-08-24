package com.example.mvvmgithub.Di.Components;


import com.example.mvvmgithub.Data.Repository.Repository;
import com.example.mvvmgithub.Di.Modules.GitHubModule;
import com.example.mvvmgithub.Data.Network.RetroFitEndpoints.GitHubApi;
import com.example.mvvmgithub.Di.Scopes.GitHubApiScope;

import dagger.Component;


@GitHubApiScope
@Component(modules = {GitHubModule.class})
public interface GitHubComponent {
    GitHubApi getGitHubApi();
}
