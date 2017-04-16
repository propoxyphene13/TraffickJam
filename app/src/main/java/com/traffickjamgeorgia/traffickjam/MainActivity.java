package com.traffickjamgeorgia.traffickjam;




import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.StreamHandler;

public class MainActivity extends AppCompatActivity {


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



    }

    @Override
    protected void onResume(){
        super.onResume();

        //Locale assignment moved to settings page, set text onResume to update for changes

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

