package com.example.halcyonjuly7.celebrityassignment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.halcyonjuly7.celebrityassignment.adapters.CelebRecyclerAdapter;
import com.example.halcyonjuly7.celebrityassignment.database.Celebrity;
import com.example.halcyonjuly7.celebrityassignment.fragments.FragmentDetail;

public class MainActivity extends AppCompatActivity implements CelebRecyclerAdapter.OnCelebClickListener {
    Fragment fragmentDetail;

    public interface FragmentCallback {
        void callBack(Object item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentDetail = getSupportFragmentManager().findFragmentByTag(FragmentDetail.FRAG_TAG);
        if (fragmentDetail == null) {
            fragmentDetail = new FragmentDetail();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_detail, fragmentDetail).commit();
        }
    }

    @Override
    public void onCelebClick(Celebrity celebrity) {
        ((FragmentCallback)fragmentDetail).callBack(celebrity);
    }

}
