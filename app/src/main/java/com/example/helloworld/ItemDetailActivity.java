package com.example.helloworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Intent in = getIntent();
        int index = in.getIntExtra("com.example.helloworld.ITEM_INDEX", -1);

        if(index > -1){
            int img = getPicture(index);
            ImageView imgView = (ImageView) findViewById(R.id.imageView);
            scaleImage(imgView, img);
        }
    }

    private int getPicture(int index){
        switch (index){
            case 0: return R.drawable.com_2198034;
            case 1: return R.drawable.atlas;
            case 2: return R.drawable.sign;
            default: return -1;
        }
    }

    private void scaleImage(ImageView img, int pic){
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imageWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if(imageWidth > screenWidth){
            int ratio = Math.round((float) imageWidth/ (float)screenWidth);
            options.inSampleSize = ratio;
        }
        options.inJustDecodeBounds = false;
        Bitmap scaleImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaleImg);

    }
}
