package com.example.mvvmgithub;

import android.app.Application;

import com.example.mvvmgithub.Data.Pojo.RepoListResponse;
import com.example.mvvmgithub.Data.Pojo.UserResponse;
import com.example.mvvmgithub.Data.Repository.Repository;
import com.example.mvvmgithub.Di.Components.DaggerUserViewModelComponent;
import com.example.mvvmgithub.Di.Modules.ContextModule;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    @Inject
    Repository repository;


    public UserViewModel(@NonNull Application application) {
        super(application);

        DaggerUserViewModelComponent.builder()
                .contextModule(new ContextModule(application.getApplicationContext()))
                .build().inject(this);
    }


    public LiveData<UserResponse> search_users(String user_name) {
        repository.getUserDataFromApi(user_name);
        return repository.getUserResponseMutableLiveData();
    }

    public LiveData<List<RepoListResponse>> search_user_repo(String user_name) {
        repository.getRepoDataFromApi(user_name);
        return repository.getRepoListResponseMutableLiveData();
    }


}
