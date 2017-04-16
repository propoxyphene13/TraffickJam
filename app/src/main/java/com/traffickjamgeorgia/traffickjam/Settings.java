package com.traffickjamgeorgia.traffickjam;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    private String selectedLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        PrefsFrag settingsPrefsFragment = new PrefsFrag();
        ft.replace(android.R.id.content, settingsPrefsFragment);
        ft.commit();

    }

    //When Settings screen is no longer visible, apply language change to locale
    //Each page will need to 'set text' when becoming visible to apply
    @Override
    protected void onPause(){
        super.onPause();


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

    }


    public static class PrefsFrag extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.settings);
        }
    }

}