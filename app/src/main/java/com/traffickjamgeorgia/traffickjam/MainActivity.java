package com.traffickjamgeorgia.traffickjam;

// Todo:
// FB twitter and instagram links on website are broken
// Facebook, Instagram and Twitter code works when app not installed, test with apps installed
//    possibly replace fb code with the Ig/Tw code... and see about making a single method for all
// Dont have a twitter account for traffick jam, make one for them? ask them to make one?
//    whatever happens, need to change the current twitter link
// Add landscape views plus any data saving necessary
// Add accessibility helper text to all non-text fields
// Remove temporary bits: button on main to Contact page
// Figure out spacing for buttons in contact.xml... maybe

// Bugs:
// Damn menu inflator text wont update with locale change, screw it.


// Assignment:
//    Functions on 4.03 and later - done
//    Implements at least 3 activities with unique layout - 2 for sure
//    At least two activities share data with intent passing - Contact passes ph# to dialer with URI
//    Implements at least one fragment - Preference fragment for settings
//    All strings loaded from strings xml - most, some non-display text is in .java
//    Presentation - ?
//    Code - ?


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

// Can't assign these any longer because they are in pager
//        missionStatementTitle=(TextView)findViewById(R.id.missionStatementTitle);
//        missionStatement=(TextView)findViewById(R.id.missionStatement);


        //ViewPager setup
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));


        // Language start
        // This must stay here because if it isn't, the app uses defaults on start...
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
        //Language end
    }

    @Override
    protected void onResume(){
        super.onResume();

//Set text within the onResume method to update for changes - superseded with app restart in settings
//        missionStatementTitle.setText(R.string.xmltxt_main_missionStatementTitle);
//        missionStatement.setText(R.string.xmltxt_main_missionStatement1);

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

    //temporary links to pages
    public void contacts(View v){
        startActivity(new Intent(this,Contact.class));
    }


    //Pager Enum
    public enum CustomPagerEnum {

        MISSION(R.string.mission, R.layout.mission_pager),
        HELP(R.string.help, R.layout.help_pager);
        //ORANGE(R.string.orange, R.layout.view_orange);

        private int mTitleResId;
        private int mLayoutResId;

        CustomPagerEnum(int titleResId, int layoutResId) {
            mTitleResId = titleResId;
            mLayoutResId = layoutResId;
        }

        public int getTitleResId() {
            return mTitleResId;
        }

        public int getLayoutResId() {
            return mLayoutResId;
        }

    }


    //PagerAdapter
    public class CustomPagerAdapter extends PagerAdapter {

        private Context mContext;

        public CustomPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
            LayoutInflater inflater = LayoutInflater.from(mContext);
            ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);
            collection.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return CustomPagerEnum.values().length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
            return mContext.getString(customPagerEnum.getTitleResId());
        }

    }

}

