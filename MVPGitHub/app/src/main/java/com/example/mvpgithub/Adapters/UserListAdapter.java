package com.example.mvpgithub.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvpgithub.Pojo.Users;
import com.example.mvpgithub.R;
import com.example.mvpgithub.Views.RepoListActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<Users> user_list;
    private Context context;

    public UserListAdapter(Context context, List<Users> user_list) {
        this.context = context;
        this.user_list = user_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users = user_list.get(position);
        Picasso.get().load(users.getAvatarUrl()).into(holder.user_image);
        holder.user_name.setText(users.getLogin());
    }

    @Override
    public int getItemCount() {
        return user_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView user_image;
        public TextView user_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_image = itemView.findViewById(R.id.user_image);
            user_name = itemView.findViewById(R.id.user_name);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, RepoListActivity.class);
                intent.putExtra("user_name", user_list.get(getAdapterPosition()).getLogin());
                context.startActivity(intent);
            });
        }
    }
}
