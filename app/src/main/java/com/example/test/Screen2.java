package com.example.test;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.net.MalformedURLException;
import java.net.URL;


public class Screen2 extends AppCompatActivity{

    WebView page;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);


        String name = "https://oauth.vk.com/authorize";

        page = (WebView) new WebView(this);
        setContentView(page);

       //page.loadUrl("https://oauth.vk.com/authorize?client_id=6043385&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,offline&response_type=code&v=5.64");
        page.loadUrl("https://oauth.vk.com/authorize?client_id=6043385&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,offline&response_type=token&v=5.64");
    }


}
