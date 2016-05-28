package com.example.olaf.weatherproject.data;

import org.json.JSONObject;

/**
 * Created by Olaf on 2016-05-28.
 */
public class Wind implements JsonP {
    private double wind_speed;

    public double getWind_speed() {
        return wind_speed;
    }

    @Override
    public void populate(JSONObject data){
        try{
        wind_speed = data.optDouble("speed");
        }catch (Exception e){}
    }
}
