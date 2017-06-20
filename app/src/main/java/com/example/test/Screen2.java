package com.example.test;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import java.net.URL;
import java.util.StringTokenizer;


import static com.example.test.R.id.web;


public class Screen2 extends AppCompatActivity{

    final String SAVED_TEXT = "save_text", SAVED_FRIEND ="save_friend";

    WebView page;
    String url1, token, tok, access_token, user_ID, friend, home, ans ,host;
    SharedPreferences sPref;
    StringTokenizer str, access;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        page = (WebView) findViewById(web);
        page.setWebViewClient(new MyWebViewClient());


        url1 = "https://oauth.vk.com/authorize?client_id=6043385&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,offline&response_type=token&v=5.65&revoke=1";
        page.loadUrl(url1);


    }

    public class MyWebViewClient extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
           view.loadUrl(url);

           token = view.getUrl();
            Uri uri = Uri.parse(token);
            tok = uri.getFragment();
            if(tok != null)
            {
            access = new StringTokenizer(tok, "&");
            access_token = access.nextToken();

                view.loadUrl("https://api.vk.com/method/friends.get?fields=photo_100,country&order=name&" + access_token);
            }


               return true;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, token, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, tok, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, access_token, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, ans, Toast.LENGTH_LONG).show();


    }
}
