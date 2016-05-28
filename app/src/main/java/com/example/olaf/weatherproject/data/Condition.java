package com.example.olaf.weatherproject.data;

import org.json.JSONObject;

/**
 * Created by Olaf on 2016-05-28.
 */
public class Condition implements JsonP {
    private int code;
    private  int temperature;
    private String text;
    private String date;

    public String getDate() {
        return date;
    }

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getText() {
        return text;
    }

    @Override
    public void populate(JSONObject data){
        try{
        code = data.optInt("code");
        date = data.optString("date");
        temperature = data.optInt("temp");
        text = data.optString("text");
        }catch (Exception e){}

    }

}
