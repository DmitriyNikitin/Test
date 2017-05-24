package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button on1screen;
    Button on2screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        on1screen =(Button) findViewById(R.id.on1screen);
        on2screen =(Button) findViewById(R.id.on2screen);

        on1screen.setOnClickListener(this);
        on2screen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.on1screen:
                Intent Firstscreen = new Intent(this, Firstscreen.class);
                startActivity(Firstscreen);
                break;
            case R.id.on2screen:
                Intent Screen2 = new Intent(this, Screen2.class);
                startActivity(Screen2);
                break;
        }
    }


}

