package com.traffickjamgeorgia.traffickjam;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        PrefsFrag settingsPrefsFragment = new PrefsFrag();
        ft.replace(android.R.id.content, settingsPrefsFragment);
        ft.commit();
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