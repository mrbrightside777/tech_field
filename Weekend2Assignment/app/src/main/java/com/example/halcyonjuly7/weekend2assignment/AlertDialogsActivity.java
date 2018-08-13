package com.example.halcyonjuly7.weekend2assignment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class AlertDialogsActivity extends AppCompatActivity {

    private CharSequence[] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialogs);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        options = getResources().getStringArray(R.array.options);
    }

    public void button_click(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        switch (view.getId()) {
            case R.id.alert_default_layout:

                builder1.setMessage("This is a default layout");
                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                break;

            case R.id.alert_custom_layout:
                builder1.setView(R.layout.alert_dialog_custom);
                break;

            case R.id.alert_options:
                builder1.setItems(getResources().getStringArray(R.array.options), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        }
        builder1.create().show();
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
