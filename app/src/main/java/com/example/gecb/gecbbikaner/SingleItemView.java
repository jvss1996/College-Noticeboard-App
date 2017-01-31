package com.example.gecb.gecbbikaner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class SingleItemView extends Activity {
    // Declare Variables
    String title;
    String content;
    String date;
    String thumbnail;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);

        Intent i = getIntent();
        title = i.getStringExtra("title");
        content = i.getStringExtra("content");
        date = i.getStringExtra("date");
        // Get the result of image
        thumbnail = i.getStringExtra("thumbnail");

        // Locate the TextViews
        TextView txt_title = (TextView) findViewById(R.id.title);
        TextView txt_content = (TextView) findViewById(R.id.content);
        TextView txt_time = (TextView) findViewById(R.id.date);

        // Locate the ImageView in singleitemview.xml
        ImageView get_img = (ImageView) findViewById(R.id.thumbnail);

        // Set results to the TextViews
        txt_title.setText(title);
        txt_content.setText(content);
        txt_time.setText(date);

        Picasso
                .with(context)
                .load(thumbnail)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.errorimage)
                .into(get_img);
    }
}
