package com.example.mvvmgithub.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvvmgithub.Adapters.UserSearchRecyclerAdapter;
import com.example.mvvmgithub.Data.Pojo.Users;
import com.example.mvvmgithub.R;
import com.example.mvvmgithub.UserViewModel;
import com.example.mvvmgithub.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    UserViewModel userViewModel;
    ActivityMainBinding activityMainBinding;
    UserSearchRecyclerAdapter adapter;
    List<Users> user_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpViewModels();
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setViewModel(userViewModel);
        adapter = new UserSearchRecyclerAdapter(user_list, this);
        activityMainBinding.weatherRecycler.setAdapter(adapter);
        activityMainBinding.weatherRecycler.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.searchUserBtn.setOnClickListener(view -> {
            searchUsers();
        });

    }


    private void  setUpViewModels() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

    }


    private void searchUsers() {
        userViewModel.search_users(activityMainBinding.githubUsernameEdit.getText().toString()).observe(this, userResponse -> {
            user_list.clear();
            user_list.addAll(userResponse.getUsers());
            adapter.notifyDataSetChanged();
        });
    }
}
