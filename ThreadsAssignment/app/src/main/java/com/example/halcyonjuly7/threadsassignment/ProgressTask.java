package com.example.halcyonjuly7.threadsassignment;

import android.widget.ProgressBar;

public class ProgressTask extends android.os.AsyncTask<Void, Integer, Void> {


    ProgressBar progressBar;

    public ProgressTask(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.setProgress(values[0]);
    }
}
