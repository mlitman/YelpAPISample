package com.example.yelpapisample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkThread extends Thread
{

    @Override
    public void run()
    {
        super.run();
        try {
            URL theURL = new URL("https://www.google.com");
            HttpURLConnection con = (HttpURLConnection)theURL.openConnection();
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
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
