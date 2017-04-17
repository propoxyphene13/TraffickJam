package com.traffickjamgeorgia.traffickjam;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Contact extends AppCompatActivity {

    private TextView contact_titleText;

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

        contact_titleText=(TextView)findViewById(R.id.contact_titleText);

     //   Delete if not needed anymore:
     //   fb=(ImageButton)findViewById(R.id.fb_btn);
     //   tw=(ImageButton)findViewById(R.id.tw_btn);
     //   ig=(ImageButton)findViewById(R.id.ig_btn);

    }

    @Override
    protected void onResume(){
        super.onResume();

        //Set text within the onResume method to update for changes

        //unnecessary???????  updates on own, why the F dont the others
//        contact_titleText.setText(R.string.xmltxt_contact_titleText);

    }

    public void onBtnClick(View v) {



        switch (v.getId()) {
            case R.id.contact_hotlineNumText:
                Intent dialer = new Intent(Intent.ACTION_DIAL);
                dialer.setData(Uri.parse("tel:18448423678"));
                startActivity(dialer);
                break;

            case R.id.fb_btn:
                startActivity(newFacebookIntent(this.getPackageManager(),"https://www.facebook.com/traffickjam.georgia/"));
                break;

            case R.id.tw_btn:
                launchTw();
                //startActivity(new Intent());
                break;

            case R.id.ig_btn:
                launchIg();
                break;
        }

    }

    // Determines if facebook app is installed and either starts that or opens in the web browser
    // http://stackoverflow.com/a/24547437/1048340
    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }


    // http://stackoverflow.com/a/23511180
    public void launchIg(){
        Uri uri = Uri.parse("https://www.instagram.com/traffickjam.georgia/");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/traffickjam.georgia/")));
        }
    }

    // http://stackoverflow.com/a/23511180
    public void launchTw(){
        Uri uri = Uri.parse("https://twitter.com/traffickjamgeorgia");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.twitter.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/traffickjamgeorgia")));
        }
    }

}
