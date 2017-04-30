package com.traffickjamgeorgia.traffickjam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    public void picClick(View vw){
        Intent home_intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(home_intent);
    }
}
