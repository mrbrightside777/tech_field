package com.example.halcyonjuly7.threadsassignment;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Random;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    Button start_task;
    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    ProgressBar progressBar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        progressBar4 = findViewById(R.id.progressBar4);

        start_task = findViewById(R.id.start_tasks);

        start_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_all_task();
            }
        });

    }


    private void update_progressbar(final ProgressBar progressBar, final int sleep) {
        final Handler handler = new Handler();
        progressBar.setProgress(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 100; index++) {
                    try {
                        Thread.sleep(sleep);
                        final int copy = index;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(copy);
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private void start_all_task() {


        update_progressbar(progressBar1, (int)(Math.random()*((5000-1000)+1))+1000);
        update_progressbar(progressBar2, (int)(Math.random()*((5000-1000)+1))+1000);
        update_progressbar(progressBar3, (int)(Math.random()*((5000-1000)+1))+1000);
        update_progressbar(progressBar4, (int)(Math.random()*((5000-1000)+1))+1000);
    }
}
