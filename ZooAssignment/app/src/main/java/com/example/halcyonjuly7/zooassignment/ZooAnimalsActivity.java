package com.example.halcyonjuly7.zooassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.halcyonjuly7.zooassignment.db.DbHelper;


public class ZooAnimalsActivity extends AppCompatActivity {

    RecyclerView animal_list;
    TextView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_animals);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DbHelper dbHelper = DbHelper.getINSTANCE(this);
        RecyclerAdapter adapter = new RecyclerAdapter(dbHelper.get_animals(getIntent().getStringExtra("category")));
        animal_list = findViewById(R.id.animal_list);
        animal_list.setAdapter(adapter);
        animal_list.setLayoutManager(new LinearLayoutManager(this));
        category = findViewById(R.id.animal_category);
        category.setText(getIntent().getStringExtra("category"));
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
}
