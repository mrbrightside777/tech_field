package com.example.halcyonjuly7.celebrityassignment.database;

import android.content.Context;
import android.database.Cursor;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "celebs.db";
    private static final int DATABASE_VERSION = 1;
    private static DbHelper INSTANCE;


    public static DbHelper getINSTANCE(Context context) {
        if (INSTANCE == null)
            INSTANCE = new DbHelper(context);
        return INSTANCE;
    }

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Celebrity> get_celebrities() {
        List<Celebrity> celebrities = new ArrayList<>();
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM CELEBS;", null);
        if (cursor.moveToFirst()) {
            do {
                celebrities.add(
                        new Celebrity(cursor.getString(cursor.getColumnIndex(Celebrity.COLUMN_NAME)),
                                cursor.getString(cursor.getColumnIndex(Celebrity.COLUMN_JOB_TITLE)),
                                cursor.getString(cursor.getColumnIndex(Celebrity.COLUMN_DESCRIPTION)),
                                cursor.getString(cursor.getColumnIndex(Celebrity.COLUMN_IMAGE))));
            } while (cursor.moveToNext());
        }
        return celebrities;
    }
}