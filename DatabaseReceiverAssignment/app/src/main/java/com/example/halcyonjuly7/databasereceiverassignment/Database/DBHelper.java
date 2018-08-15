package com.example.halcyonjuly7.databasereceiverassignment.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "my.db";
    public static final int VERSION = 1;

    public static DBHelper instance;

    public static DBHelper getInstance(Context context) {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }


    private DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE  IF NOT EXISTS %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT," +
                "%s INTEGER," +
                "%s INTEGER," +
                "%s TEXT);",
                Person.TABLE_NAME,
                Person.COLUMN_ID,
                Person.COLUMN_NAME,
                Person.COLUMN_AGE,
                Person.COLUMN_WEIGHT,
                Person.COLUMN_GENDER));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("DROP TABLE %s", Person.TABLE_NAME));
        onCreate(sqLiteDatabase);
    }


    public void add_person(Person person) {
        getWritableDatabase().execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('%s', %s, %s, '%s');",
                Person.TABLE_NAME,
                Person.COLUMN_NAME,
                Person.COLUMN_AGE,
                Person.COLUMN_WEIGHT,
                Person.COLUMN_GENDER,
                person.getName(),
                person.getAge(),
                person.getWeight(),
                person.getGender()));

    }

    public List<Person> get_all_people() {
        List<Person> personList = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery(String.format("SELECT * FROM %s",Person.TABLE_NAME), null);

        if (cursor.moveToFirst()) {
            do {
                personList.add(new Person(cursor.getString(cursor.getColumnIndex(Person.COLUMN_NAME)),
                        cursor.getInt(cursor.getColumnIndex(Person.COLUMN_AGE)),
                        cursor.getInt(cursor.getColumnIndex(Person.COLUMN_WEIGHT)),
                        cursor.getString(cursor.getColumnIndex(Person.COLUMN_GENDER))
                        ));

            } while (cursor.moveToNext());
        }

        return personList;
    }
}
