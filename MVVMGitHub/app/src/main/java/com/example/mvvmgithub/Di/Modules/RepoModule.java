package com.example.mvvmgithub.Di.Modules;

import android.content.Context;

import com.example.mvvmgithub.Data.Repository.Repository;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class RepoModule {

    @Provides
    Repository getRepo(Context context) {
        return new Repository(context);
    }
}
