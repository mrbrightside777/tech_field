package com.example.mvpgithub.Contracts;

import com.example.mvpgithub.Pojo.RepoListResponse;
import com.example.mvpgithub.Pojo.UserResponse;
import com.example.mvpgithub.Pojo.Users;

import java.util.List;

import io.reactivex.Single;

public interface MainActivityContract {
    interface View extends BaseView {
        void display_user_info(List<Users> users);


    }

    interface Model extends BaseModel {
        Single<UserResponse> get_user(String user_id);
        Single<List<RepoListResponse>>  get_user_repo(String user_id);
    }

    interface Presenter extends BasePresenter{
        void populate_users(String user_id);
    }


}
