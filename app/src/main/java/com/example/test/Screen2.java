package com.example.test;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;



import java.util.StringTokenizer;


import static com.example.test.R.id.web;


public class Screen2 extends AppCompatActivity{

    final String SAVED_TEXT = "save_text";

    WebView page;
    String url1, token, tok, access_token, user_ID;
    SharedPreferences sPref;
    StringTokenizer str, access;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        page = (WebView) findViewById(web);

        page.setWebViewClient(new MyWebViewClient());
        url1 = "https://oauth.vk.com/authorize?client_id=6043385&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,offline&response_type=token&v=5.64&revoke=1";
        page.loadUrl(url1);


    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            token = page.getUrl();
            str = new StringTokenizer(token, "#");

            str.nextToken();
            tok = str.nextToken();

            access = new StringTokenizer(tok, "&");
            access_token = access.nextToken();
            access.nextToken();
            user_ID = access.nextToken();

            sPref = getSharedPreferences("Token",MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(SAVED_TEXT, access_token);
            ed.commit();
            return true;
        }
    }
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, token, Toast.LENGTH_LONG).show();
        Toast.makeText(this, tok, Toast.LENGTH_LONG).show();
        Toast.makeText(this, access_token, Toast.LENGTH_LONG).show();
        Toast.makeText(this, user_ID, Toast.LENGTH_LONG).show();


    }*/
}
