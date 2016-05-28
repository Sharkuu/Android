package com.example.olaf.weatherproject.data;

import org.json.JSONObject;

/**
 * Created by Olaf on 2016-05-28.
 */
public class Forecast implements JsonP {
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

    @Override
    public void populate(JSONObject data){
        try{
        code = data.optInt("code");
        date = data.optString("date");
        high = data.getInt("high");
        low = data.getInt("low");
        text = data.optString("text");
        }catch (Exception e){}

    }
}
