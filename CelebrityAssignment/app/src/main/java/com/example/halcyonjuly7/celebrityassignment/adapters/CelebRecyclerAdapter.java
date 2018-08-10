package com.example.halcyonjuly7.celebrityassignment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.halcyonjuly7.celebrityassignment.R;
import com.example.halcyonjuly7.celebrityassignment.database.Celebrity;

import org.w3c.dom.Text;

import java.util.List;

public class CelebRecyclerAdapter extends RecyclerView.Adapter<CelebRecyclerAdapter.ViewHolder> {

    private List<Celebrity> celeb_names;
    private OnCelebClickListener listener;

    public interface OnCelebClickListener {
        void onCelebClick(Celebrity celebrity);
    }


    public CelebRecyclerAdapter(List<Celebrity>celeb_names, OnCelebClickListener listener) {
        this.celeb_names = celeb_names;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView celeb_name;

        public ViewHolder(View itemView) {
            super(itemView);
            celeb_name = itemView.findViewById(R.id.celeb_item_name);
        }

        public void bind(final Celebrity celebrity, final OnCelebClickListener listener) {
            celeb_name.setOnClickListener(view -> listener.onCelebClick(celebrity));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celeb_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.celeb_name.setText(celeb_names.get(position).getName());
        holder.bind(celeb_names.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return celeb_names.size();
    }
}
