package com.example.halcyonjuly7.actionsapp;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Activity ctx;

    private EditText number;
    private Button call_button;
    private Integer CALL_PERMISSION = 0;



    private EditText email;
    private EditText message;
    private Button send_email;

    private EditText url;
    private Button open_browser;

    private EditText text_number;
    private EditText text_message;
    private Button send_text;


    private ImageView gallery_image;
    private Button open_gallery;

    public static final Integer GALLERY_PERMISSION = 1;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this;
        number = findViewById(R.id.number_edittext);
        call_button = findViewById(R.id.call_button);
        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    call_number(number.getText().toString());
                } else {
                    ActivityCompat.requestPermissions(ctx, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION);
                }
            }
        });

        email = findViewById(R.id.email_target);
        message = findViewById(R.id.email_msg);
        send_email = findViewById(R.id.send_email);
        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_target = email.getText().toString();
                String email_message = message.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email_target});
                intent.putExtra(Intent.EXTRA_TEXT, email_message);
                try {
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } catch (ActivityNotFoundException e) {

                }

            }
        });

        url = findViewById(R.id.url);
        open_browser = findViewById(R.id.open_browser);
        open_browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String target_url = url.getText().toString();
                if (target_url.length() != 0) {
                    if (!target_url.startsWith("http://") || !target_url.startsWith("https://"))
                        target_url = String.format("http://%s", target_url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(target_url)));
                    startActivity(intent);
                }
            }
        });


        text_number = findViewById(R.id.text_number);
        text_message = findViewById(R.id.text_message);
        send_text = findViewById(R.id.send_text);
        send_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = text_number.getText().toString();
                String msg = text_message.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(String.format("smsto:%s", num)));
                intent.putExtra("sms_body", msg);
                startActivity(intent);
            }
        });

        gallery_image = findViewById(R.id.gallery_image);
        open_gallery = findViewById(R.id.open_gallery);
        open_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    choose_picture();
                } else
                    ActivityCompat.requestPermissions(ctx, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_PERMISSION);
            }
        });

    }


    @SuppressWarnings({"MissingPermission"})
    private void call_number(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(String.format("tel:%s", number)));
        startActivity(intent);

    }

    private void choose_picture() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_PERMISSION);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                call_number(number.getText().toString());
                break;
            case 1:
                choose_picture();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (data != null) {
                    Uri image_uri = data.getData();
                    String[] filepath = new String[] {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(image_uri, filepath, null, null, null);
                    cursor.moveToFirst();
                    String picture_path = cursor.getString(cursor.getColumnIndex(filepath[0]));
                    gallery_image.setImageBitmap(BitmapFactory.decodeFile(picture_path));
                }

        }
    }
}
