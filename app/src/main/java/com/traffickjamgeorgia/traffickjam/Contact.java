package com.traffickjamgeorgia.traffickjam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Contact extends AppCompatActivity {

    //Insert contact methods...
    // Text is for info
    // Image buttons are for types like twitter, fb, instagram...
    // hope to make these launch those respective apps



    // Delete if not needed anymore:
    // ImageButton fb;
    // ImageButton tw;
    // ImageButton ig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

     //   Delete if not needed anymore:
     //   fb=(ImageButton)findViewById(R.id.fb_btn);
     //   tw=(ImageButton)findViewById(R.id.tw_btn);
     //   ig=(ImageButton)findViewById(R.id.ig_btn);

    }

    public void onBtnClick(View v) {

        // http://stackoverflow.com/questions/4810803/open-facebook-page-from-android-app

        switch (v.getId()) {
            case R.id.fb_btn:
                startActivity(new Intent());
                break;

            case R.id.tw_btn:
                startActivity(new Intent());
                break;

            case R.id.ig_btn:
                startActivity(new Intent());
                break;
        }

    }

}
