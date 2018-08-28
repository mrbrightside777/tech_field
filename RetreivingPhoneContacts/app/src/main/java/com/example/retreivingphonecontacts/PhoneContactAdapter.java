package com.example.retreivingphonecontacts;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.util.Pair;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class PhoneContactAdapter extends ArrayAdapter<String> implements Filterable {

    List<Pair<String, String>> people_names;
    Context context;



    public PhoneContactAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        people_names = new ArrayList<>();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<Pair<String, String>> results = ContactsHelper.read_phone_contacts(constraint.toString(), context);

                    people_names.clear();
                    people_names.addAll(results);
                    filterResults.values = results;
                    filterResults.count = results.size();
                }
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0)
                    notifyDataSetChanged();
                else
                    notifyDataSetInvalidated();

            }
        };
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return String.format("%s: %s", people_names.get(position).first,people_names.get(position).second) ;
    }

    @Override
    public int getCount() {
        return people_names.size();
    }

    public Pair<String, String> get_pair(int position) {
        return people_names.get(position);
    }

}
