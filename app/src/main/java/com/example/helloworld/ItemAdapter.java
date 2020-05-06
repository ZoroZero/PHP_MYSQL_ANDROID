package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
    LayoutInflater mInfoInflater;
    public String[] items;
    public String[] descriptions;
    public String[] prices;

    public ItemAdapter(Context context, String[] items, String[] descriptions, String[] prices){
        this.items = items;
        this.descriptions = descriptions;
        this.prices = prices;
        mInfoInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInfoInflater.inflate(R.layout.my_list_view_detail, null);
        // Get view component
        TextView nameText = (TextView) v.findViewById(R.id.nameTextView);
        TextView desText = (TextView) v.findViewById(R.id.descriptTextView);
        TextView priceText = (TextView) v.findViewById(R.id.priceTextView);
        // Get name from array
        String name = items[position];
        String description = descriptions[position];
        String price = prices[position];
        // Set texts
        nameText.setText(name);
        desText.setText(description);
        priceText.setText(price);

        return v;
    }
}
