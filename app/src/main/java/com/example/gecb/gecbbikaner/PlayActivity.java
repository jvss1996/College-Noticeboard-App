package com.example.gecb.gecbbikaner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play);

        YoYo.with(Techniques.FadeIn)
                .duration(3000)
                .playOn(findViewById(R.id.imageView));
        YoYo.with(Techniques.SlideInDown)
                .duration(3000)
                .playOn(findViewById(R.id.button1));

        ImageButton button1 = (ImageButton) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void home_page (View view){
        goToUrl("http://ecb.ac.in/");
    }
    private void goToUrl(String url){
        Uri uri1 = Uri.parse(url);
        Intent launchbrowser = new Intent(Intent.ACTION_VIEW, uri1);
        startActivity(launchbrowser);
    }
}
