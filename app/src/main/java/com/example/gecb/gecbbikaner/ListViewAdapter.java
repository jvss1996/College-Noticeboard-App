package com.example.gecb.gecbbikaner;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data1;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data1 = arraylist;
    }

    @Override
    public int getCount() {
        return data1.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView title;
        TextView content;
        TextView date;
        final ImageView thumbnail;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_post, parent, false);
        // Get the position
        resultp = data1.get(position);

        // Locate the TextViews
        title = (TextView) itemView.findViewById(R.id.title);
        content = (TextView) itemView.findViewById(R.id.content);
        date = (TextView) itemView.findViewById(R.id.date);

        // Locate the ImageView
        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

        // Capture position and set results to the TextViews
        title.setText(resultp.get(NoticeboardActivity.TITLE));
        content.setText(resultp.get(NoticeboardActivity.CONTENT));
        date.setText(resultp.get(NoticeboardActivity.DATE));

        //Type Picasso
        Picasso
                .with(context)
                .load(resultp.get(NoticeboardActivity.THUMBNAIL))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.errorimage)
                .resize(250,250)
                .into(thumbnail);

        // Capture ListView item click
        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                resultp = data1.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                intent.putExtra("title", resultp.get(NoticeboardActivity.TITLE));
                intent.putExtra("content", resultp.get(NoticeboardActivity.CONTENT));
                intent.putExtra("date", resultp.get(NoticeboardActivity.DATE));
                intent.putExtra("thumbnail", resultp.get(NoticeboardActivity.THUMBNAIL));
                context.startActivity(intent);
            }
        });
        /*thumbnail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                resultp = data1.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                //intent.putExtra("thumbnail", resultp.get(NoticeboardActivity.THUMBNAIL));
                //context.startActivity(intent);
                intent.setAction(Intent.ACTION_VIEW);
                Uri imguri = Uri.parse(resultp.get(NoticeboardActivity.THUMBNAIL));
                intent.setDataAndType(imguri, "thumbnail");
                context.startActivity(intent);
            }
        });*/
        return itemView;
    }
}
