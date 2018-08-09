package com.example.halcyonjuly7.zooassignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.halcyonjuly7.zooassignment.db.Animal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Animal> animals;

    public RecyclerAdapter(List<Animal> animals) {
        this.animals = animals;
    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animals_recyler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Animal animal = animals.get(position);

        holder.name.setText(animal.getName());
        holder.scientific_name.setText(animal.getScientific_name());
        holder.description.setText(animal.getDescription());
        if (!animal.getImage_url().isEmpty())
            Picasso.get().load(animal.getImage_url()).into(holder.image);
        else
            Picasso.get().load(R.mipmap.ic_launcher).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        TextView scientific_name;
        TextView description;
        ImageView image;
        
        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.animal_name);
            scientific_name = itemView.findViewById(R.id.animal_scientific_name);
            description = itemView.findViewById(R.id.animal_description);
            image = itemView.findViewById(R.id.animal_image);

        }
    }
}
