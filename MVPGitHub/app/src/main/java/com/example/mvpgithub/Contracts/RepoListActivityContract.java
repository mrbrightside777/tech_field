package com.example.mvpgithub.Contracts;

import com.example.mvpgithub.Pojo.RepoListResponse;

import java.util.List;

import io.reactivex.Single;

public interface RepoListActivityContract {
    interface View extends BaseView {
        void update_repo_recyler(List<RepoListResponse> repo_list);
    }
    interface Model extends BaseModel {
        Single<List<RepoListResponse>> get_user_repos(String user_name);
    }

    interface Presenter extends BasePresenter {
        void populate_view_repo_list(String user_name);
    }

}
