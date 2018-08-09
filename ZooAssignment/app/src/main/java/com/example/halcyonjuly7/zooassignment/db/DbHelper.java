package com.example.halcyonjuly7.zooassignment.db;

import android.content.Context;
import android.database.Cursor;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "animals.db";
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

    public List<Animal> get_animals(String category) {
        Cursor cursor = getReadableDatabase().rawQuery(String.format("SELECT * FROM %s WHERE CATEGORY = '%s';", Animal.TABLE_NAME, category.toLowerCase()), null);
        List<Animal> animals = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                animals.add(new Animal(cursor.getString(cursor.getColumnIndex(Animal.COLUMN_CATEGORY)),
                        cursor.getString(cursor.getColumnIndex(Animal.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(Animal.COLUMN_SCIENTIFIC_NAME)),
                        cursor.getString(cursor.getColumnIndex(Animal.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(Animal.COLUMN_IMAGE_URL))
                ));
            }while (cursor.moveToNext());
        }
        return animals;
    }
}


