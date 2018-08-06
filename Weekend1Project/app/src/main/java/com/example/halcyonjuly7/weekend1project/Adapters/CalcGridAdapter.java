package com.example.halcyonjuly7.weekend1project.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.halcyonjuly7.weekend1project.CalculatorFragment;
import com.example.halcyonjuly7.weekend1project.R;


public class CalcGridAdapter extends BaseAdapter {

    String[] data;
    Context context;



    public CalcGridAdapter(Context context, String[] data) {
        this.data = data;
        this.context = context;
    }


    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = inflater.inflate(R.layout.grid_view_items, null);
        TextView number = item_view.findViewById(R.id.grid_view_item);
        number.setText(data[i]);
        return item_view;
    }
}
