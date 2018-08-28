package com.example.retreivingphonecontacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper {
    public static List<Pair<String, String>> read_phone_contacts(String name, Context context) {
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        String selection = projection[0] + " LIKE ?";
        String[] selectionArgs = new String[]{name + "%"};

        Cursor phoneCursor = context.getContentResolver()
                .query(phoneUri, projection, selection, selectionArgs, null);
        List<Pair<String, String>> people_numbers = new ArrayList<>();
        String person_number = "";
        String person_name = "";
        if (phoneCursor.moveToFirst())
            do {
                person_number = phoneCursor.getString(phoneCursor.getColumnIndex(projection[1]));
                person_name = phoneCursor.getString(phoneCursor.getColumnIndex(projection[0]));
                people_numbers.add(new Pair(person_name, person_number));
            } while (phoneCursor.moveToNext());
        return people_numbers;
    }
}
