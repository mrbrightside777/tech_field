package com.example.mvvmgithub.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmgithub.Data.Pojo.UserResponse;
import com.example.mvvmgithub.Data.Pojo.Users;
import com.example.mvvmgithub.R;
import com.example.mvvmgithub.Views.RepoListActivity;
import com.example.mvvmgithub.databinding.UserSearchRecyclerItemsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class UserSearchRecyclerAdapter extends RecyclerView.Adapter<UserSearchRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Users> users;

    public UserSearchRecyclerAdapter(List<Users> users, Context context) {
        this.users = users;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserSearchRecyclerItemsBinding bindings =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_search_recycler_items, parent, false);
        return new ViewHolder(bindings);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Users current_user = users.get(position);
        Picasso.get().load(current_user.getAvatarUrl()).into(holder.binding.userImage);
        holder.binding.userLogin.setText(current_user.getLogin());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        UserSearchRecyclerItemsBinding binding;

        public ViewHolder(@NonNull UserSearchRecyclerItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(itemView -> {
                Intent intent = new Intent(context, RepoListActivity.class);
                intent.putExtra("user_name", users.get(getAdapterPosition()).getLogin());
                context.startActivity(intent);

            });
        }
    }
}
