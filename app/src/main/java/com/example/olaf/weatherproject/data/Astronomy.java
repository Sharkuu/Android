package com.example.olaf.weatherproject.data;

import org.json.JSONObject;

/**
 * Created by Olaf on 2016-05-28.
 */
public class Astronomy implements JsonP {
    private String sunrise;
    private String sunset;

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    @Override
    public void populate(JSONObject data){
        try {
            sunrise = data.optString("sunrise");
            sunset = data.optString("sunset");
        }catch (Exception e){}

    }

}
