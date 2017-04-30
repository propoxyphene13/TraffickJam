package com.traffickjamgeorgia.traffickjam;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Youth extends AppCompatActivity {

    private Button gemsBtn, btjBtn, hopeBtn;
    private ImageView bannerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youth);

        gemsBtn = (Button)findViewById(R.id.gemsBtn);
        btjBtn = (Button)findViewById(R.id.btjBtn);
        hopeBtn = (Button)findViewById(R.id.hopeBtn);
        bannerImg = (ImageView)findViewById(R.id.traffickJamBanner);
    }

    public void onBtnClick(View vw){
        switch(vw.getId()){
            case R.id.gemsBtn:
                goToUrl(getString(R.string.gems_link));
                break;
            case R.id.btjBtn:
                goToUrl(getString(R.string.btj_link));
                break;
            case R.id.hopeBtn:
                goToUrl(getString(R.string.hope_link));
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
