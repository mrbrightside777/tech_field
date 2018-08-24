package com.example.mvvmgithub.Di.Modules;


import com.example.mvvmgithub.Data.Network.RetroFitEndpoints.GitHubApi;
import com.example.mvvmgithub.Di.Scopes.GitHubApiScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = RetroFitModule.class)
public class GitHubModule {

    @Provides
    @GitHubApiScope
    GitHubApi getApiHandler(Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }
}
