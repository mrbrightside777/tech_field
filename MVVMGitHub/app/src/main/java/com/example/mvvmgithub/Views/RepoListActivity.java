package com.example.mvvmgithub.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvvmgithub.Adapters.RepoListAdapter;
import com.example.mvvmgithub.Adapters.UserSearchRecyclerAdapter;
import com.example.mvvmgithub.Data.Pojo.RepoListResponse;
import com.example.mvvmgithub.R;
import com.example.mvvmgithub.UserViewModel;
import com.example.mvvmgithub.databinding.ActivityRepoListBinding;

import java.util.List;

public class RepoListActivity extends AppCompatActivity {

    private RecyclerView repo_recycler;
    private ActivityRepoListBinding binding;
    private UserViewModel userViewModel;
    private RepoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_repo_list);
        binding.setLifecycleOwner(this);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.search_user_repo(getIntent().getStringExtra("user_name")).observe(this, repoListResponses -> {
            binding.repolistRecycler.setAdapter(new RepoListAdapter(repoListResponses));
            binding.repolistRecycler.setLayoutManager(new LinearLayoutManager(this));
        });

    }
}
