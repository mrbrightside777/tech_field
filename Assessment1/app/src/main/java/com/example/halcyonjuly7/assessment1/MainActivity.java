package com.example.halcyonjuly7.assessment1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.halcyonjuly7.assessment1.fragements.Fragment1;
import com.example.halcyonjuly7.assessment1.fragements.Fragment2;

public class MainActivity extends AppCompatActivity implements Fragment1.Fragment1Listener {

    Fragment fragment1;
    Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager frag_manager = getSupportFragmentManager();

        fragment1 = frag_manager.findFragmentByTag(Fragment1.FRAG_TAG);
        if (fragment1 == null)
            fragment1 = new Fragment1();
        fragment2 = frag_manager.findFragmentByTag(Fragment2.FRAG_TAG);
        if (fragment2 == null)
            fragment2 = new Fragment2();


        FragmentTransaction frag_trans = frag_manager.beginTransaction();
        frag_trans.replace(R.id.fragment1, fragment1, Fragment1.FRAG_TAG);
        frag_trans.replace(R.id.fragment2, fragment2, Fragment2.FRAG_TAG);
        frag_trans.commit();

    }

    @Override
    public void onAddCar(Car car) {
        ((Fragment2)fragment2).add_car(car);
    }
}
