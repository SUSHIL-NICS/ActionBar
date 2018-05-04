package com.example.sushil.actionbar;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sushil on 02-05-2017.
 */
public class BackgroundTask extends AsyncTask<Void, Void, Void>{
    String string_url="http://192.168.1.141/login.php";
    @Override
    protected Void doInBackground(Void... voids) {

        try{
            URL url=new URL(string_url);
            HttpURLConnection  httpURLConnection=(HttpURLConnection)url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader((new InputStreamReader(inputStream)));
            StringBuilder stringBuilder=new StringBuilder();
            String line;

            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
            httpURLConnection.disconnect();
            String json_string=stringBuilder.toString().trim();
            Log.d("JSON STRING",json_string);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
