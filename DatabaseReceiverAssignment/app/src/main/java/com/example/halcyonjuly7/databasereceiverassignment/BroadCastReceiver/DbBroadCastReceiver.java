package com.example.halcyonjuly7.databasereceiverassignment.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.halcyonjuly7.databasereceiverassignment.Database.DBHelper;
import com.example.halcyonjuly7.databasereceiverassignment.Database.Person;

public class DbBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Person person = (Person) intent.getSerializableExtra("person");
        DBHelper.getInstance(context).add_person(person);
    }
}
