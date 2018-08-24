package com.example.mvvmgithub.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmgithub.Data.Pojo.RepoListResponse;
import com.example.mvvmgithub.R;
import com.example.mvvmgithub.databinding.RepoRecyclerItemsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private List<RepoListResponse> repos;

    public RepoListAdapter(List<RepoListResponse> repos) {this.repos = repos;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RepoRecyclerItemsBinding binding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.repo_recycler_items, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RepoListResponse repoListResponse = repos.get(position);
        Picasso.get().load(repoListResponse.getOwner().getAvatarUrl()).into(holder.binding.imageView);
        holder.binding.repoDescription.setText(repoListResponse.getDescription());
        holder.binding.repoName.setText(repoListResponse.getName());
        holder.binding.repoFollowerCount.setText(String.valueOf(repoListResponse.getStargazersCount()));
        holder.binding.repoStarCount.setText(String.valueOf(repoListResponse.getStargazersCount()));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RepoRecyclerItemsBinding binding;

        public ViewHolder(@NonNull RepoRecyclerItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
