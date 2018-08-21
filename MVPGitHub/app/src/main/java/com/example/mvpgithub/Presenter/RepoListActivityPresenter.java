package com.example.mvpgithub.Presenter;

import com.example.mvpgithub.Contracts.RepoListActivityContract;
import com.example.mvpgithub.Models.RepoListModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepoListActivityPresenter implements RepoListActivityContract.Presenter {
    private RepoListActivityContract.View view;
    private RepoListActivityContract.Model model;

    public RepoListActivityPresenter(RepoListActivityContract.View view) {
        this.view = view;
    }
    @Override
    public void initPresenter() {
        model = new RepoListModel();
        model.initModel();
    }

    @Override
    public void populate_view_repo_list(String user_name) {
        model.get_user_repos(user_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoListResponse -> {
                    view.update_repo_recyler(repoListResponse);
                });
    }
}
