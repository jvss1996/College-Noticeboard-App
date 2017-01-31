package com.example.gecb.gecbbikaner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

        //title = (TextView)findViewById(R.id.title_view);
        //content = (TextView) findViewById(R.id.post);
        //postedat = (TextView) findViewById(R.id.posted_at);
        //time = (TextView) findViewById(R.id.date_post);
        final ImageView thumbnail = (ImageView) findViewById(R.id.thumbnail);

        //postList = new ArrayList<>();
        //listView = (ListView) findViewById(R.id.data);

        Button home = (Button) findViewById(R.id.homebtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeboardActivity.this,MainActivity.class));
            }
        });
        //Here it is imageView click listener

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
            //HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            //String jsonStr = sh.makeServiceCall("https://gecbcom.000webhostapp.com/api/get_posts/?count=10");

            jsonobject = JSONfunctions.getJSONfromURL("https://gecbcom.000webhostapp.com/api/get_posts/?count=10");

            //Log.e(TAG, "Response from url: " + jsonStr);

            //if (jsonStr != null) {
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
                        //thumbnail = jsonobject.getString("thumbnail");
                        //map.put("thumbnail", thumbnail);
                        map.put("thumbnail", jsonobject.getString("thumbnail"));
                        map.put("content", content);

                        arrayList.add(map);
                    }
                } catch (JSONException e) {
                    Log.e("Json parsing error: ", e.getMessage());
                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });*/
                    e.printStackTrace();
                }
            //}
            /*else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Check your Internet Connection", Toast.LENGTH_LONG).show();
                    }
                });
            }*/
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            //super.onPostExecute(result);
            // Dismiss the progress dialog
            //if (pDialog.isShowing())
                //pDialog.dismiss();

            /**
             * Updating parsed JSON data into ListView
             * */
            //New Try
            listview = (ListView) findViewById(R.id.data);
            adapter = new ListViewAdapter(NoticeboardActivity.this, arrayList);

            /*ListAdapter adapter = new SimpleAdapter(
                    NoticeboardActivity.this, postList,
                    R.layout.list_post, new String[]{"title", "content_get", "datepost"}, new int[]
                    {R.id.title_view, R.id.post, R.id.date_post}); */

            listview.setAdapter(adapter);
            pDialog.dismiss();
        }

    }
}
