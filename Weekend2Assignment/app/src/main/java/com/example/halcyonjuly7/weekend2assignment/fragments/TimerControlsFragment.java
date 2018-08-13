package com.example.halcyonjuly7.weekend2assignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.halcyonjuly7.weekend2assignment.R;

public class TimerControlsFragment extends Fragment {

    public static String FRAG_TAG = "timer_controls";
    public interface TimerControls {
        void start();
        void stop();
    }

    private Button timer_start,timer_stop;
    private TimerControls timer_callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TimerControls)
            timer_callback = (TimerControls)context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timercontrols, container, false);

        timer_start = view.findViewById(R.id.timer_start);
        timer_stop = view.findViewById(R.id.timer_stop);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        timer_start.setOnClickListener(view -> timer_callback.start());
        timer_stop.setOnClickListener(view -> timer_callback.stop());
    }
}
