package com.example.mvpgithub.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvpgithub.Pojo.RepoListResponse;
import com.example.mvpgithub.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private List<RepoListResponse> repo_list;

    public RepoListAdapter(List<RepoListResponse> repo_list) {
        this.repo_list = repo_list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repolist_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RepoListResponse repoListResponse = repo_list.get(position);

        Picasso.get().load(repoListResponse.getOwner().getAvatarUrl()).into(holder.user_image);
        holder.star_count.setText(String.valueOf(repoListResponse.getStargazersCount()));
        holder.watch_count.setText(String.valueOf(repoListResponse.getWatchersCount()));
        holder.repo_description.setText(repoListResponse.getDescription());
        holder.repo_name.setText(repoListResponse.getName());
        holder.repo_language.setText(repoListResponse.getLanguage());
    }

    @Override
    public int getItemCount() {
        return repo_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView user_image;
        public TextView star_count;
        public TextView watch_count;
        public TextView repo_description;
        public TextView repo_name;
        public TextView repo_language;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_image = itemView.findViewById(R.id.user_image);
            star_count = itemView.findViewById(R.id.repo_star_count);
            watch_count = itemView.findViewById(R.id.repo_watch_count);
            repo_description = itemView.findViewById(R.id.repo_description);
            repo_name = itemView.findViewById(R.id.repo_name);
            repo_language = itemView.findViewById(R.id.repo_language);

        }
    }
}
