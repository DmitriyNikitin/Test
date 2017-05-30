package com.example.test;



import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import java.util.StringTokenizer;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.example.test.R.id.web;


public class Screen2 extends AppCompatActivity{

    WebView page;
    String url1, token, http, tok, access_token;
    SharedPreferences fpref;
    StringTokenizer str, access;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
/*
        WebView webView = (WebView) findViewById(R.id.web);
        Uri data = getIntent().getData();
        webView.loadUrl(data.toString());
*/
        page = (WebView) findViewById(web);
        page.setWebViewClient(new MyWebViewClient());
       // page.getSettings().getBuiltInZoomControls();

        url1 = "https://oauth.vk.com/authorize?client_id=6043385&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,offline&response_type=token&v=5.64&revoke=1";
      // Save_token ="client_id=6043385&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,offline&response_type=token&v=5.64&revoke=1";
        //page.loadUrl("https://oauth.vk.com/authorize?client_id=6043385&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,offline&response_type=code&v=5.64");
        page.loadUrl(url1);





    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            token = page.getUrl();
            str = new StringTokenizer(token, "#");

            http = str.nextToken();
            tok = str.nextToken();

            access = new StringTokenizer(tok, "&");
            access_token = access.nextToken();
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, token, Toast.LENGTH_LONG).show();
        Toast.makeText(this, tok, Toast.LENGTH_LONG).show();
        Toast.makeText(this, access_token, Toast.LENGTH_LONG).show();
    }
}
