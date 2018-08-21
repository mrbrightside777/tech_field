package com.example.mvpgithub.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mvpgithub.Adapters.RepoListAdapter;
import com.example.mvpgithub.Contracts.RepoListActivityContract;
import com.example.mvpgithub.Pojo.RepoListResponse;
import com.example.mvpgithub.Presenter.RepoListActivityPresenter;
import com.example.mvpgithub.R;

import java.util.ArrayList;
import java.util.List;

public class RepoListActivity extends AppCompatActivity implements RepoListActivityContract.View {

    private RecyclerView recyclerView;
    private RepoListAdapter adapter;
    private List<RepoListResponse> repo_list;
    private RepoListActivityContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        presenter = new RepoListActivityPresenter(this);
        presenter.initPresenter();
        initView();
        presenter.populate_view_repo_list(getIntent().getStringExtra("user_name"));
    }

    @Override
    public void initView() {
        repo_list = new ArrayList<>();
        adapter = new RepoListAdapter(repo_list);
        recyclerView = findViewById(R.id.repo_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void update_repo_recyler(List<RepoListResponse> repo_list) {
        this.repo_list.clear();
        this.repo_list.addAll(repo_list);
        adapter.notifyDataSetChanged();

    }
}
