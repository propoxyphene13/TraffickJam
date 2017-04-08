package com.traffickjamgeorgia.traffickjam;


//need to figure out how to apply lang changes right away...



import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.logging.StreamHandler;

public class MainActivity extends AppCompatActivity {

    private String selectedLang;

    //xml objects
    private ImageView banner;
    private TextView missionStatementTitle;
    private TextView missionStatement;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign xml objects
        banner=(ImageView)findViewById(R.id.traffickJamBanner);
        missionStatementTitle=(TextView)findViewById(R.id.missionStatementTitle);
        missionStatement=(TextView)findViewById(R.id.missionStatement);

        //On Create, pull selected lang from storage
        //If it's not set (or still set to default) - pull lang from device
        SharedPreferences settingobj;
        settingobj = PreferenceManager.getDefaultSharedPreferences(this);

        selectedLang = settingobj.getString("appLang","");

        Locale locale;
        Configuration cfg = new Configuration();

        if (selectedLang.equals("default")){   //pull from sys
            selectedLang = Locale.getDefault().getLanguage();
        }
        locale = new Locale(selectedLang);
        cfg.locale = locale;
        getResources().updateConfiguration(cfg,null);

        missionStatementTitle.setText(R.string.xmltxt_main_missionStatementTitle);
        missionStatement.setText(R.string.xmltxt_main_missionStatement1);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the application bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_settings_id:

                startActivity(new Intent(this, Settings.class));
                break;
        }
        return true;
    }


    

}

