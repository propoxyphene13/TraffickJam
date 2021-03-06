package com.traffickjamgeorgia.traffickjam;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class resources extends AppCompatActivity {

    private Button ncmecBtn, nfsBtn;
    private ImageView bannerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        ncmecBtn = (Button)findViewById(R.id.ncmecBtn);
        nfsBtn = (Button)findViewById(R.id.nfsBtn);
        bannerImg = (ImageView)findViewById(R.id.traffickJamBanner);
    }

    public void onBtnClick(View vw){
        switch(vw.getId()){
            case R.id.ncmecBtn:
                goToUrl(getString(R.string.ncmec_link));
                break;
            case R.id.nfsBtn:
                goToUrl(getString(R.string.nfs_link));
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
