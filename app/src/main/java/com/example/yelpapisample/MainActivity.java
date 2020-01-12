package com.example.yelpapisample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NetworkThread nt = new NetworkThread();
        //nt.start();
        Thread t = new Thread() {
            @Override
            public void run()
            {
                super.run();
                try {
                    URL theURL = new URL("https://api.yelp.com/v3/businesses/search?location=Mequon, WI&categories=restaurants");
                    HttpURLConnection con = (HttpURLConnection)theURL.openConnection();
                    con.setRequestProperty("Authorization", "Bearer _fudvZjjQRmQpuzZTXjRDPxJeLlYw8rGTsrzZNknmb_B7AA7ilLpaYgwHl-nM8UmgEKWI3rs7DPYseiuGzUmEybSLzLiGIiQVLFExouUcl-mKZQtHTymfnB1AgDJXHYx");
                    con.setRequestMethod("GET");
                    con.setReadTimeout(15*1000);
                    con.connect();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    StringBuilder stringBuilder = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        stringBuilder.append(line + "\n");
                    }
                    System.out.println(stringBuilder.toString());
                    JSONObject obj = new JSONObject(stringBuilder.toString());
                    JSONArray theBusinesses = (JSONArray)obj.get("businesses");
                    for(int i = 0; i < theBusinesses.length(); i++)
                    {
                        JSONObject business = (JSONObject)theBusinesses.get(i);
                        System.out.println(business.get("name"));
                    }

                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        };
        t.start();

    }
}
