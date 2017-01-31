package com.example.gecb.gecbbikaner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FacilitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);
    }
    public void hostel_page (View view){
        goToUrl("http://ecb.ac.in/college-hostels/");
    }
    private void goToUrl(String url){
        Uri uri1 = Uri.parse(url);
        Intent launchbrowser = new Intent(Intent.ACTION_VIEW, uri1);
        startActivity(launchbrowser);
    }
    public void transport_page (View view){
        goToUrl1("http://ecb.ac.in/college-transportation/");
    }
    private void goToUrl1(String url){
        Uri uri1 = Uri.parse(url);
        Intent launchbrowser = new Intent(Intent.ACTION_VIEW, uri1);
        startActivity(launchbrowser);
    }
    public void morefaci_page (View view){
        goToUrl2("http://ecb.ac.in/more-facilities/");
    }
    private void goToUrl2(String url){
        Uri uri1 = Uri.parse(url);
        Intent launchbrowser = new Intent(Intent.ACTION_VIEW, uri1);
        startActivity(launchbrowser);
    }
}
