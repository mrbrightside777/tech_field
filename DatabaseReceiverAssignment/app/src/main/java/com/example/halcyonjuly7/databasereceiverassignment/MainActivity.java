package com.example.halcyonjuly7.databasereceiverassignment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.halcyonjuly7.databasereceiverassignment.BroadCastReceiver.DbBroadCastReceiver;
import com.example.halcyonjuly7.databasereceiverassignment.Database.Person;

public class MainActivity extends AppCompatActivity {

    EditText person_name, person_age, person_weight, person_gender;
    Button add_person, go_to_person_list;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        person_name = findViewById(R.id.person_name);
        person_age = findViewById(R.id.person_age);
        person_weight = findViewById(R.id.person_weight);
        person_gender = findViewById(R.id.person_gender);
        add_person = findViewById(R.id.add_person);
        go_to_person_list = findViewById(R.id.go_to_person_list);

        add_person.setOnClickListener(view -> {

            Person person = new Person(person_name.getText().toString(),
                    Integer.parseInt(person_age.getText().toString()),
                    Integer.parseInt(person_weight.getText().toString()),
                    person_gender.getText().toString());

            Intent intent = new Intent();
            intent.putExtra("person", person);
            intent.setAction(getPackageName() + ".add_person");
            sendBroadcast(intent);

            person_name.setText("");
            person_age.setText("");
            person_weight.setText("");
            person_gender.setText("");
        });

        go_to_person_list.setOnClickListener(view -> {
            Intent intent = new Intent(this, PersonListActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        broadcastReceiver = new DbBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getPackageName() + ".add_person");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}
