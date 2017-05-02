package com.traffickjamgeorgia.traffickjam;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.content.ContextCompat;
import java.util.Locale;
import java.util.logging.StreamHandler;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private String selectedLang;

    //xml objects
    private ImageView banner;
    private TextView missionStatementTitle;
    private TextView missionStatement;
    private TextView hotline, number;
    private Spinner about_us, resources, more;
    private ViewFlipper slideshow;
    Animation fade_in, fade_out;
    int oncreate = 0;
    int initialized = 0;
    private boolean isDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideshow = (ViewFlipper) this.findViewById(R.id.title_viewFlipper);
        fade_in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        slideshow.setInAnimation(fade_in);
        slideshow.setOutAnimation(fade_out);

        //sets auto flipping
        slideshow.setAutoStart(true);
        slideshow.setFlipInterval(5000);
        slideshow.startFlipping();

        //assign xml objects
        banner=(ImageView)findViewById(R.id.traffickJamBanner);
        hotline = (TextView)findViewById(R.id.hotline);
        number = (TextView)findViewById(R.id.number);

        about_us = (Spinner) findViewById(R.id.aboutus_spinner);
        resources = (Spinner) findViewById(R.id.resources_spinner);
        more = (Spinner) findViewById(R.id.more_spinner);

        String[] aboutus_array = getResources().getStringArray(R.array.aboutus_array);
        String[] resources_array = getResources().getStringArray(R.array.resources_array);
        String[] more_array = getResources().getStringArray(R.array.more_array);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aboutus_array);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resources_array);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, more_array);

// Apply the adapter to the spinner
        about_us.setAdapter(adapter1);
        resources.setAdapter(adapter2);
        more.setAdapter(adapter3);

        about_us.setOnItemSelectedListener(aboutusListener);
        resources.setOnItemSelectedListener(resourcesListener);
        more.setOnItemSelectedListener(moreListener);

// Can't assign these any longer because they are in pager
//        missionStatementTitle=(TextView)findViewById(R.id.missionStatementTitle);
//        missionStatement=(TextView)findViewById(R.id.missionStatement);


        //ViewPager setup
        //ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        //viewPager.setAdapter(new CustomPagerAdapter(this));


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
        isDefault = true;
    }

    private Spinner.OnItemSelectedListener aboutusListener = new Spinner.OnItemSelectedListener(){

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if(isDefault) {
                isDefault = false;
            }
            else{
                if (about_us.getItemAtPosition(pos).equals(getString(R.string.array_aboutus))) {
                    Intent aboutus_intent = new Intent(getBaseContext(), About.class);
                    startActivity(aboutus_intent);
                } else if (about_us.getItemAtPosition(pos).equals(getString(R.string.array_history))) {
                    Intent history_intent = new Intent(getBaseContext(), History.class);
                    startActivity(history_intent);
                } else if (about_us.getItemAtPosition(pos).equals(getString(R.string.array_hallmarks))) {
                    Intent hallmarks_intent = new Intent(getBaseContext(), Hallmarks.class);
                    startActivity(hallmarks_intent);
                }
            }
            ((TextView)parent.getChildAt(0)).setTextSize(9);
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private Spinner.OnItemSelectedListener resourcesListener = new Spinner.OnItemSelectedListener(){

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if(isDefault) {
                isDefault = false;
            }
            else {
                if (resources.getItemAtPosition(pos).equals(getString(R.string.array_youthleaders))) {
                    Intent youthleaders_intent = new Intent(getBaseContext(), resources.class);
                    startActivity(youthleaders_intent);
                } else if (resources.getItemAtPosition(pos).equals(getString(R.string.array_youth))) {
                    Intent youth_intent = new Intent(getBaseContext(), Youth.class);
                    startActivity(youth_intent);
                } else if (resources.getItemAtPosition(pos).equals(getString(R.string.array_contact))) {
                    Intent contact_intent = new Intent(getBaseContext(), Contact.class);
                    startActivity(contact_intent);
                }
            }
            ((TextView)parent.getChildAt(0)).setTextSize(9);
        }
        public void onNothingSelected(AdapterView<?> parent) {}
    };

    private Spinner.OnItemSelectedListener moreListener = new Spinner.OnItemSelectedListener(){

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if(isDefault) {
                isDefault = false;
            }
            else {
                if (more.getItemAtPosition(pos).equals(getString(R.string.array_fund))) {
                    goToUrl(getString(R.string.fundraiser_link));
                } else if (more.getItemAtPosition(pos).equals(getString(R.string.array_shop))) {
                    Intent shop_intent = new Intent(getBaseContext(), Shop.class);
                    startActivity(shop_intent);
                }
            }
            ((TextView)parent.getChildAt(0)).setTextSize(9);
        }
        public void onNothingSelected(AdapterView<?> parent) {}
    };

    public void blogClick(View vw){
        goToUrl (getString(R.string.blog_link));
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public void onHotlineClick(View vw){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(getString(R.string.call_num)));
            startActivity(callIntent);
        }
        else {
            Intent dialer = new Intent(Intent.ACTION_DIAL);
            dialer.setData(Uri.parse(getString(R.string.call_num)));
            startActivity(dialer);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

//Set text within the onResume method to update for changes - superseded with app restart in settings
//        missionStatementTitle.setText(R.string.xmltxt_main_missionStatementTitle);
//        missionStatement.setText(R.string.xmltxt_main_missionStatement1);

    }

/*This is the Settings Menu, uncomment this block to add it
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the application bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_settings_id:

                startActivity(new Intent(this, Settings.class));
                break;
        }
        return true;
    }


/* Pager Adapter Removed, code left for future use if needed

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
 */
}

