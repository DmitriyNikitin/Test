package com.example.test;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;



import static com.example.test.R.id.web;


public class Screen2 extends AppCompatActivity{

    WebView page;
    String url, token;

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
        page.setWebViewClient(new WebViewClient());
        page.getSettings().getBuiltInZoomControls();

        url = "https://oauth.vk.com/authorize?client_id=6043385&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,offline&response_type=token&v=5.64&revoke=1";
       //page.loadUrl("https://oauth.vk.com/authorize?client_id=6043385&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,offline&response_type=code&v=5.64");
        page.loadUrl(url);




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        token = page.getUrl();
        Toast.makeText(this, token, Toast.LENGTH_LONG).show();
    }
}
