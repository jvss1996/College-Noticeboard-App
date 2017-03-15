package com.example.gecb.gecbbikaner;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SingleItemView extends Activity {
    // Declare Variables
    String title;
    String content;
    String date;
    String thumbnail;
    Context context;
    Button btn_share;

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

        btn_share = (Button) findViewById(R.id.button_share_image);

        // Locate the TextViews
        TextView txt_title = (TextView) findViewById(R.id.title);
        TextView txt_content = (TextView) findViewById(R.id.content);
        TextView txt_time = (TextView) findViewById(R.id.date);

        // Locate the ImageView in singleitemview.xml
        final ImageView get_img = (ImageView) findViewById(R.id.thumbnail);

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

        /*get_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri imguri = Uri.parse(thumbnail);
                intent.setDataAndType(imguri, "image/*");
                startActivity(intent);
            }
        });*/
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri bmpUri = getLocalBitmapUri(get_img);
                if (bmpUri != null) {
                    // Construct a ShareIntent with link to image
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/*");
                    // Launch sharing dialog for image
                    startActivity(Intent.createChooser(shareIntent, "Share Image"));
                    //Toast.makeText(getApplicationContext(), "Image Saved to Downloads Folder", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error Sharing Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public Uri getLocalBitmapUri(ImageView imageView) {
        if (!(imageView.getDrawable() instanceof BitmapDrawable)) {
            return null;
        }
        Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Uri bmpUri = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "gecb_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            return Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return bmpUri;
        }
    }
}
