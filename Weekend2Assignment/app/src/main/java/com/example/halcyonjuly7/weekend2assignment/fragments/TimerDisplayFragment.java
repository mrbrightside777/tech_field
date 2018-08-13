package com.example.halcyonjuly7.weekend2assignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.halcyonjuly7.weekend2assignment.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TimerDisplayFragment extends Fragment {

    public static String FRAG_TAG="timer_display";
    private TextView timer_text;
    private Long current_time = 0L;
    private boolean stop;
    private Observer<Long> observer;
    private Observable<Long> observable;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        observable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .takeWhile(interval -> !stop)
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timerdisplay, container, false);
        timer_text = view.findViewById(R.id.timer_text);
        observer = new Observer<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Long aLong) {
                timer_text.setText(String.valueOf(current_time++));
            }
        };
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void start_timer() {
        stop = false;
        current_time = 0L;
        timer_text.setText("0");
        observable.subscribe(observer);


    }

    public void stop_timer() {
        stop = true;
        current_time = 0L;
    }


}
