package com.example.mvpgithub.Presenter;



import com.example.mvpgithub.Contracts.MainActivityContract;
import com.example.mvpgithub.Models.GithubModel;
import com.example.mvpgithub.Pojo.Users;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View view;
    private MainActivityContract.Model model;

    public MainActivityPresenter(MainActivityContract.View view) {this.view = view;}

    @Override
    public void initPresenter() {

        model = new GithubModel();
        model.initModel();
    }

    @Override
    public void populate_users(String user_id) {
        model.get_user(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(userResponse -> {
                    return Single.just(userResponse.getUsers());

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Users>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Users> users) {
                        view.display_user_info(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
//                .subscribe(users -> {
//                    view.display_user_info(users);
//                });
    }
}
