package com.example.retreivingphonecontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private static final int READ_CONTACTS_PERMISSON = 0;
    private AutoCompleteTextView autoCompleteTextView;
    PhoneContactAdapter phoneContactAdapter;
    private Pair<String, String> pair;
    private static final int CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView = findViewById(R.id.person_name);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        READ_CONTACTS_PERMISSON);

        } else {
            phoneContactAdapter = new PhoneContactAdapter(this, android.R.layout.simple_list_item_1);
            autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    pair = phoneContactAdapter.get_pair(position);
                }
            });
        }
        autoCompleteTextView.setAdapter(phoneContactAdapter);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pair = null;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    public void readPhoneContacts(View view) {
        if (pair != null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE);
            } else {
                if (pair != null) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + pair.second));
                    startActivity(intent);
                }
            }
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.READ_CONTACTS},
                        READ_CONTACTS_PERMISSON);
            } else {
                Log.d(TAG, "checkPermission: requesting permission");
                ContactsHelper.read_phone_contacts(autoCompleteTextView.getText().toString(), this);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_CONTACTS_PERMISSON:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    phoneContactAdapter = new PhoneContactAdapter(this, android.R.layout.simple_list_item_1);
                    autoCompleteTextView.setAdapter(phoneContactAdapter);
                    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            pair = phoneContactAdapter.get_pair(position);
                        }
                    });
//                    ContactsHelper.read_phone_contacts(autoCompleteTextView.getText().toString(), this);
                }
                break;
            case CALL_PHONE:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + pair.second));
                startActivity(intent);
                break;
        }

    }
}
