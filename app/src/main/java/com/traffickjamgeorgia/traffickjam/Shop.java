package com.traffickjamgeorgia.traffickjam;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Shop extends AppCompatActivity {

    private Button braceletBtn, shirtBtn, tankBtn, wristbandBtn, headbandBtn, decalBtn;
    private ImageView bannerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        braceletBtn = (Button)findViewById(R.id.braceletBtn);
        shirtBtn = (Button)findViewById(R.id.shirtBtn);
        tankBtn = (Button)findViewById(R.id.tankBtn);
        wristbandBtn = (Button)findViewById(R.id.wristbandBtn);
        headbandBtn = (Button)findViewById(R.id.headbandBtn);
        decalBtn = (Button)findViewById(R.id.decalBtn);
        bannerImg = (ImageView)findViewById(R.id.traffickJamBanner);
    }

    public void onBtnClick(View vw){
        switch(vw.getId()){
            case R.id.braceletBtn:
                goToUrl(getString(R.string.bracelet_link));
                break;
            case R.id.shirtBtn:
                goToUrl(getString(R.string.shirt_link));
                break;
            case R.id.tankBtn:
                goToUrl(getString(R.string.tank_link));
                break;
            case R.id.wristbandBtn:
                goToUrl(getString(R.string.wristband_link));
                break;
            case R.id.headbandBtn:
                goToUrl(getString(R.string.headband_link));
                break;
            case R.id.decalBtn:
                goToUrl(getString(R.string.decal_link));
                break;
            case R.id.traffickJamBanner:
                Intent home_intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(home_intent);
                break;
        }
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
