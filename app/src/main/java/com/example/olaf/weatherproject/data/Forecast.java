package com.example.olaf.weatherproject.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Olaf on 2016-05-28.
 */
public class Forecast  {
private int code;
    private String date;
    private int high;
    private int low;
    private String text;


    public String getDate() {
        return date;
    }

    public int getCode() {
        return code;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

    public String getText() {
        return text;
    }

    public int getAvg(){return (getHigh()+getLow())/2;}
    public void populate(JSONArray dat,int i){
        try{
            JSONObject data = dat.getJSONObject(i);
        code = data.optInt("code");
        date = data.optString("date");
        high = data.getInt("high");
        low = data.getInt("low");
        text = data.optString("text");
        }catch (Exception e){}

    }
}
