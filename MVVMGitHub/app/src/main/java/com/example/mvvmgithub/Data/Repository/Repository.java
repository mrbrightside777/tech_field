package com.example.mvvmgithub.Data.Repository;

import android.content.Context;

import com.example.mvvmgithub.Data.Database.Databases.MvvmDB;
import com.example.mvvmgithub.Data.Database.Entities.UserEntity;
import com.example.mvvmgithub.Data.Network.RetroFitEndpoints.GitHubApi;
import com.example.mvvmgithub.Data.Pojo.RepoListResponse;
import com.example.mvvmgithub.Data.Pojo.UserResponse;
import com.example.mvvmgithub.Di.Components.DaggerDbComponent;
import com.example.mvvmgithub.Di.Components.DaggerGitHubComponent;
import com.example.mvvmgithub.Di.Modules.ContextModule;
import com.example.mvvmgithub.Di.Modules.DbModule;
import com.example.mvvmgithub.Di.Modules.OkhttpModule;
import com.example.mvvmgithub.Di.Modules.RetroFitModule;
import com.example.mvvmgithub.Misc.Constants;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    GitHubApi gitHubApi;
    MvvmDB mvvmDB;

    MutableLiveData<UserResponse> userResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<RepoListResponse>> repoListResponseMutableLiveData = new MutableLiveData<>();


    public Repository(Context context) {
        gitHubApi = DaggerGitHubComponent
                .builder()
                .retroFitModule(new RetroFitModule(Constants.WEB.GITHUB_BASE_URL))
                .okhttpModule(new OkhttpModule(Constants.WEB.headers))
                .build().getGitHubApi();

        mvvmDB = DaggerDbComponent.builder()
                .contextModule(new ContextModule(context))
                .build()
                .getDb();

    }


    public void getUserDataFromApi(String username) {
        Single.fromCallable(() -> {
            return gitHubApi.search_user(username).execute().body();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(UserResponse userResponse) {
                        userResponseMutableLiveData.setValue(userResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void getRepoDataFromApi(String username) {
        Single.fromCallable(() -> {
            return gitHubApi.get_user_repo(username).execute().body();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<RepoListResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<RepoListResponse> repoListResponses) {
                        repoListResponseMutableLiveData.setValue(repoListResponses);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    public MutableLiveData<UserResponse> getUserResponseMutableLiveData() {return userResponseMutableLiveData;}
    public MutableLiveData<List<RepoListResponse>> getRepoListResponseMutableLiveData() {return repoListResponseMutableLiveData;}
}
