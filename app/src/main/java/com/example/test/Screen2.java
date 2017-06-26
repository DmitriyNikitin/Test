package com.example.test;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


import static com.example.test.R.id.web;

    public class Screen2 extends AppCompatActivity{

        private String TAG = MainActivity.class.getSimpleName();

        private ProgressDialog pDialog;
        private ListView lv;


        WebView page;
        String url1, token, tok, access_token;
        SharedPreferences sPref;
        StringTokenizer str, access;

        // URL to get contacts JSON
        private static String JSON = null;
        ArrayList<HashMap<String, String>> contactList;


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

                    JSON = "https://api.vk.com/method/friends.get?fields=photo_100,country&order=name&" + access_token;
                   // view.loadUrl(JSON);
                    view.setVisibility(view.GONE);
                    contactList = new ArrayList<>();

                    lv = (ListView) findViewById(R.id.list);
                    new GetContacts().execute();
                }


                return true;
            }
        }

        /**
         * Async task class to get json by making HTTP call
         */
        private class GetContacts extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Showing progress dialog
                pDialog = new ProgressDialog(Screen2.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();

            }

            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String jsonStr = sh.makeCall(JSON);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("response");

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);

                            String id = c.getString("uid");
                            String first_name = c.getString("first_name");
                            String last_name = c.getString("last_name");
                            String photo_100 = c.getString("photo_100");
                            String online = c.getString("online");

                            if(online != "0"){
                                online = "online";
                            }else{
                                online = " ";
                            }


                            // tmp hash map for single contact
                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("uid", id);
                            contact.put("first_name", first_name);
                            contact.put("last_name", last_name);
                            contact.put("photo_100", photo_100);
                            contact.put("online", online);


                            // adding contact to contact list
                            contactList.add(contact);
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();
                /**
                 * Updating parsed JSON data into ListView
                 * */
                ListAdapter adapter = new SimpleAdapter(
                        Screen2.this, contactList,
                        R.layout.list_item, new String[]{"photo_100", "first_name",
                        "last_name", "online"}, new int[]{
                         R.id.img, R.id.first_name, R.id.last_name, R.id.online});

                lv.setAdapter(adapter);
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