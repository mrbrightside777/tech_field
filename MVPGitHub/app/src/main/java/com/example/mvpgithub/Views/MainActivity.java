package com.example.mvpgithub.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvpgithub.Adapters.UserListAdapter;
import com.example.mvpgithub.Contracts.MainActivityContract;
import com.example.mvpgithub.Pojo.Users;
import com.example.mvpgithub.Presenter.MainActivityPresenter;
import com.example.mvpgithub.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    private MaterialButton search_user;
    private TextInputEditText editText;
    private MainActivityPresenter presenter;
    private RecyclerView user_recycler;
    private List<Users> user_list;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        presenter.initPresenter();
        initView();
    }

    @Override
    public void initView() {
        search_user = findViewById(R.id.search_user);
        editText = findViewById(R.id.username_input);
        search_user.setOnClickListener( view -> {
            presenter.populate_users(editText.getText().toString());
        });
        user_list = new ArrayList<>();
        adapter = new UserListAdapter(this, user_list);
        user_recycler = findViewById(R.id.user_recycler);
        user_recycler.setAdapter(adapter);
        user_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void display_user_info(List<Users> users) {
        this.user_list.clear();
        this.user_list.addAll(users);
        adapter.notifyDataSetChanged();
    }
}
