package com.example.test;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;


public class Screen2 extends AppCompatActivity{

WebView page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        page = (WebView) new WebView(this);
        setContentView(page);

        page.loadUrl("https://oauth.vk.com/authorize?client_id=6043385&display=mobile&redirect_uri=&scope=friends&response_type=code&v=5.64");
    }


}
