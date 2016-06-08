package com.example.olaf.weatherproject.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.olaf.weatherproject.data.Channel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Olaf on 2016-05-28.
 */
public class YahooWeather {
    private WeatherCallback callback;
    private String location;
    private Exception error;

    public YahooWeather(WeatherCallback callback){
this.callback = callback;

    }
    public String getLocation(){
        return location;
    }

    public void refreashWeather( String location){
        this.location = location;
        new AsyncTask<String,Void,String>(){
            @Override
            protected String doInBackground(String... strings) {
                String YQL =   String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'",strings[0]);
                String endp = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
                try{
                URL url = new URL(endp);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        result.append(line);

                    }
                    return result.toString();

                }
                catch (Exception e){
                    error = e;
                    return null;

                }

            }

            @Override
            protected void onPostExecute(String s) {
                if(s == null && error !=null) {
                    callback.serviceFail(error);
                    return;
                }

                try{
                JSONObject data = new JSONObject(s);
                JSONObject queryRes=  data.optJSONObject("query");
                int count  = queryRes.getInt("count");
                if(count == 0){
                    return;
                }
                Channel channel = new Channel();
                channel.populate(queryRes.optJSONObject("results").optJSONObject("channel"));
                callback.serviceSuccess(channel);
                }catch (Exception e){e.printStackTrace();}

            }
        }.execute(location);

    }
}
