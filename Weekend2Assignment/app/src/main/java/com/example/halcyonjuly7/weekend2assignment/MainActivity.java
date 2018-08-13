package com.example.halcyonjuly7.weekend2assignment;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.halcyonjuly7.weekend2assignment.Dialogs.PictureDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

public class MainActivity extends AppCompatActivity {

    private String[] needed_permissions = new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private final int STORAGE_REQ_CODE = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    private void view_pdf() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File pdf = new File(getFilesDir(), "pdf_sample.pdf");

        copyFromAssets(pdf, "pdf_sample.pdf");
        Uri uri = FileProvider.getUriForFile(this, "com.example.halcyonjuly7.fileprovider", pdf);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void button_click(View view) {
        switch (view.getId()) {
            case R.id.view_pdf:
                PermissionHelper permissionHelper = new PermissionHelper(this);
                String[] permissions = permissionHelper.check_permissions(needed_permissions);
                if (permissions.length != 0)
                    permissionHelper.requestPermissions(permissions, STORAGE_REQ_CODE);
                else
                    view_pdf();
                break;
            case R.id.show_dialog:
                final PictureDialog pictureDialog = new PictureDialog();
                pictureDialog.show(getSupportFragmentManager(), PictureDialog.FRAG_TAG);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pictureDialog.dismiss();
                    }
                }, 3000);
                break;

            case R.id.go_to_timer:
                Intent intent1 = new Intent(this, TimerActivity.class);
                startActivity(intent1);
                break;


            case R.id.create_notification:
                Intent intent2 = new Intent(this, NotificationActivity.class);
                intent2.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent2, 0);
                Notification notif  = new NotificationCompat.Builder(this, "Notifs")
                        .setContentTitle("SWAG")
                        .setContentText("More swag")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setAutoCancel(true)
                        .build();
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    /* Create or update. */
                    NotificationChannel channel = new NotificationChannel("Notifs",
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    mNotificationManager.createNotificationChannel(channel);
                }
                mNotificationManager.notify(1, notif);
                break;


            case R.id.go_to_sms:
                Intent intent3 = new Intent(this, TextActivity.class);
                startActivity(intent3);
                break;


            case R.id.go_to_dialogs:
                Intent intent4 = new Intent(this, AlertDialogsActivity.class);
                startActivity(intent4);
                break;

        }
    }

    private void copyFromAssets(File file, String filename) {
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = assetManager.open(filename);
            outputStream = new FileOutputStream(file);
            copyAssetsToInternal(inputStream, outputStream);
            inputStream.close();
            outputStream.flush();
            outputStream.close();
            outputStream = null;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void copyAssetsToInternal(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
            out.write(buffer, 0, read);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_REQ_CODE:
                if (checkGrantResults(grantResults)) {
                    view_pdf();
                }

        }
    }

    public boolean checkGrantResults(int[] grantResults) {
        if (grantResults.length != 0) {
            for (int res : grantResults) {
                if (res != PermissionChecker.PERMISSION_GRANTED)
                    return false;
            }

            return true;
        }
        return false;
    }
}
