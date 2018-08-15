package com.example.halcyonjuly7.databasereceiverassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.halcyonjuly7.databasereceiverassignment.Adapters.PersonRecyclerAdapter;
import com.example.halcyonjuly7.databasereceiverassignment.Database.DBHelper;
import com.example.halcyonjuly7.databasereceiverassignment.Database.Person;

import java.util.List;

public class PersonListActivity extends AppCompatActivity {
    RecyclerView person_recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        List<Person> personList = DBHelper.getInstance(this).get_all_people();
        PersonRecyclerAdapter adapter = new PersonRecyclerAdapter(personList);
        person_recycler = findViewById(R.id.person_recycler);
        person_recycler.setAdapter(adapter);
        person_recycler.setLayoutManager(new LinearLayoutManager(this));

    }
}
