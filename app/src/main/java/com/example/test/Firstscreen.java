package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Firstscreen extends AppCompatActivity implements View.OnClickListener{

    Button OnMainScreen;
    Button on2screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstscreen);

        OnMainScreen = (Button) findViewById(R.id.OnMainScreen);
        on2screen = (Button) findViewById(R.id.on2screen);

        OnMainScreen.setOnClickListener(this);
        on2screen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.OnMainScreen:
                Intent MainScreen = new Intent(this, MainActivity.class);
                startActivity(MainScreen);
                break;
            case R.id.on2screen:
                Intent Screen2= new Intent(this, Screen2.class);
                startActivity(Screen2);
            }
        }
}

