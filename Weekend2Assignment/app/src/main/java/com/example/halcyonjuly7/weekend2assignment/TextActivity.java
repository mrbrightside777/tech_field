package com.example.halcyonjuly7.weekend2assignment;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import java.security.Permission;

public class TextActivity extends AppCompatActivity {

    private EditText message_edit, message_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        message_edit = findViewById(R.id.text_message);
        message_number = findViewById(R.id.phone_number);
    }



    public void send_message(View view) {
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PermissionChecker.PERMISSION_GRANTED) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(message_number.getText().toString(), null, message_edit.getText().toString(), null, null);
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length != 0 && grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(message_number.getText().toString(), null, message_edit.getText().toString(), null, null);

        }
    }
}
