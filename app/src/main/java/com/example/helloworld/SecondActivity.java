package com.example.helloworld;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    ListView myListView;
    String[] items;
    String[] descriptions;
    String[] prices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if(getIntent().hasExtra("com.example.helloworld.text")){
            TextView title = (TextView) findViewById(R.id.textView_SecondPage_Title);
            title.setText(getIntent().getStringExtra("com.example.helloworld.text"));
        }

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);

        items = res.getStringArray(R.array.items);
        descriptions = res.getStringArray(R.array.description);
        prices = res.getStringArray(R.array.prices);
        // Adapter
        ItemAdapter itemAdapter = new ItemAdapter(this, items, descriptions, prices);
        myListView.setAdapter(itemAdapter);

        // Set up click
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showPictureActivity = new Intent(getApplicationContext(), ItemDetailActivity.class);
                showPictureActivity.putExtra("com.example.helloworld.ITEM_INDEX", position);
                startActivity(showPictureActivity);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
