package com.example.halcyonjuly7.amazonmaterialweekend.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halcyonjuly7.amazonmaterialweekend.Pojo.Item;
import com.example.halcyonjuly7.amazonmaterialweekend.R;


import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {

    List<Item> books;

    public BookRecyclerAdapter(List<Item> books) {
        this.books = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView book_image;
        public TextView book_name;

        public ViewHolder(View itemView) {
            super(itemView);
            book_image = itemView.findViewById(R.id.book_thumbnail);
            book_name = itemView.findViewById(R.id.book_name);

        }
    }
}
