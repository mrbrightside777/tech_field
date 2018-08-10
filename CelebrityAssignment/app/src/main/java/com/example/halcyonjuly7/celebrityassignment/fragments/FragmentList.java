package com.example.halcyonjuly7.celebrityassignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.halcyonjuly7.celebrityassignment.R;
import com.example.halcyonjuly7.celebrityassignment.adapters.CelebRecyclerAdapter;
import com.example.halcyonjuly7.celebrityassignment.database.Celebrity;
import com.example.halcyonjuly7.celebrityassignment.database.DbHelper;

import java.util.List;

public class FragmentList extends Fragment {

    private List<Celebrity> celebrityList;
    private DbHelper dbHelper;
    private RecyclerView recyclerView;
    private CelebRecyclerAdapter.OnCelebClickListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CelebRecyclerAdapter.OnCelebClickListener) {
            dbHelper = DbHelper.getINSTANCE(context);
            listener = (CelebRecyclerAdapter.OnCelebClickListener) context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_layout, container, false);

        recyclerView = view.findViewById(R.id.celeb_names_recycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        celebrityList = dbHelper.get_celebrities();
        CelebRecyclerAdapter adapter = new CelebRecyclerAdapter(celebrityList, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
