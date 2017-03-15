package com.example.gecb.gecbbikaner;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class NoticeboardActivity extends Activity {
    //public String url = "https://gecbcom.000webhostapp.com/api/get_posts/?count=10";
    ProgressDialog pDialog;
    ListView listview;
    ArrayList<HashMap<String, String>> arrayList;
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListViewAdapter adapter;
    static String TITLE = "title";
    static String CONTENT = "content";
    static String DATE = "date";
    static String THUMBNAIL = "thumbnail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticeboard);

        //final ImageView thumbnail = (ImageView) findViewById(R.id.thumbnail);

        Button home = (Button) findViewById(R.id.homebtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeboardActivity.this,MainActivity.class));
            }
        });
        new GetData().execute();
    }



    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(NoticeboardActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            arrayList = new ArrayList<HashMap<String, String>>();

            jsonobject = JSONfunctions.getJSONfromURL("https://gecbcom.000webhostapp.com/api/get_posts/?count=10");

                try {

                    // Getting JSON Array node
                    jsonarray = jsonobject.getJSONArray("posts");

                    // looping through All Contacts
                    for (int i = 0; i < jsonarray.length(); i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        jsonobject = jsonarray.getJSONObject(i);

                        String get_content = jsonobject.getString("content");
                        String get_content1 = Html.fromHtml(get_content).toString().replaceAll("\n", " ").trim();
                        String content = Html.fromHtml(get_content1).toString().replaceAll("/p", " ").trim();

                        map.put("title", jsonobject.getString("title"));
                        map.put("date", jsonobject.getString("date"));
                        //getting full sized thumbnail image
                        JSONObject thumbnail_images = jsonobject.getJSONObject("thumbnail_images");
                        JSONObject thumbnail_full = thumbnail_images.getJSONObject("full");
                        map.put("thumbnail", thumbnail_full.getString("url"));
                        //map.put("thumbnail", jsonobject.getString("thumbnail"));
                        map.put("content", content);

                        arrayList.add(map);
                    }
                } catch (JSONException e) {
                    Log.e("Json parsing error: ", e.getMessage());
                    e.printStackTrace();
                } catch (RuntimeException e) {
                    Log.e("Runtime Exception: ", e.getMessage());
                }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            /**
             * Updating parsed JSON data into ListView
             * */
            listview = (ListView) findViewById(R.id.data);
            adapter = new ListViewAdapter(NoticeboardActivity.this, arrayList);
            listview.setAdapter(adapter);
            pDialog.dismiss();
        }
    }
}
