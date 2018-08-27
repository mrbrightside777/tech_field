package com.example.googleplacesassignment;

import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.ObservableField;

public class AutoCompleteObservableFields {
    public final ObservableField<String> current_query = new ObservableField<>();
    public final ObservableField<String> current_category = new ObservableField<>();
    public final ObservableField<Integer> category_pos = new ObservableField<>();


    public void categorySelected(AdapterView<?> parent, View view, int pos, long id) {

        if (category_pos.get() == null) {
            category_pos.set(pos);
            parent.setSelection(0);
        }
        else {

            category_pos.set(pos);
            current_category.set(parent.getAdapter().getItem(pos).toString());
            parent.setSelection(pos);
        }

    }
}
