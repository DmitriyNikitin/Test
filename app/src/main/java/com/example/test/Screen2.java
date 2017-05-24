package com.example.test;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Screen2 extends AppCompatActivity implements View.OnClickListener {

    Button OnMainScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        OnMainScreen = (Button) findViewById(R.id.OnMainScreen);
        OnMainScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.OnMainScreen:
                Intent OnMainScreen = new Intent(this, MainActivity.class);
                startActivity(OnMainScreen);
                break;
        }
    }
}
