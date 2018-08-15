package com.example.halcyonjuly7.databasereceiverassignment.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.halcyonjuly7.databasereceiverassignment.Database.Person;
import com.example.halcyonjuly7.databasereceiverassignment.R;

import java.util.List;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.ViewHolder> {

    private List<Person> personList;

    public PersonRecyclerAdapter(List<Person> personList) { this.personList = personList;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = personList.get(position);

        holder.person_name.setText(person.getName());
        holder.person_age.setText(String.valueOf(person.getAge()));
        holder.person_weight.setText(String.valueOf(person.getWeight()));
        holder.person_gender.setText(person.getGender());
    }



    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView person_name;
        TextView person_age;
        TextView person_weight;
        TextView person_gender;

        public ViewHolder(View itemView) {
            super(itemView);

            person_name = itemView.findViewById(R.id.person_name);
            person_age = itemView.findViewById(R.id.person_age);
            person_weight = itemView.findViewById(R.id.person_weight);
            person_gender = itemView.findViewById(R.id.person_gender);
        }
    }
}
