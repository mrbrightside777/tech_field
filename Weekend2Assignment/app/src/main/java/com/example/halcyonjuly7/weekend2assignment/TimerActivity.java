package com.example.halcyonjuly7.weekend2assignment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.halcyonjuly7.weekend2assignment.fragments.TimerControlsFragment;
import com.example.halcyonjuly7.weekend2assignment.fragments.TimerDisplayFragment;

public class TimerActivity extends AppCompatActivity implements TimerControlsFragment.TimerControls {

    private Fragment timerDisplayFragment;
    private Fragment timerControlsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        timerDisplayFragment = getSupportFragmentManager().findFragmentByTag(TimerDisplayFragment.FRAG_TAG);
        if (timerDisplayFragment == null)
            timerDisplayFragment = new TimerDisplayFragment();

        timerControlsFragment = getSupportFragmentManager().findFragmentByTag(TimerControlsFragment.FRAG_TAG);
        if (timerControlsFragment == null)
            timerControlsFragment = new TimerControlsFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.timer_display, timerDisplayFragment);
        transaction.replace(R.id.time_controls, timerControlsFragment);
        transaction.commit();

    }

    @Override
    public void start() {
        ((TimerDisplayFragment) timerDisplayFragment).start_timer();
    }

    @Override
    public void stop() {
        ((TimerDisplayFragment) timerDisplayFragment).stop_timer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
