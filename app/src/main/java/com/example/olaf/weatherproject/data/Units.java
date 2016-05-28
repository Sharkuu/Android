package com.example.olaf.weatherproject.data;

import org.json.JSONObject;

/**
 * Created by Olaf on 2016-05-28.
 */
public class Units implements JsonP {
    private String temp_U;
    private String speed_U;

    public String getSpeed() {
        return speed_U;
    }

    public String getTemp() {
        return temp_U;
    }

    @Override
    public void populate(JSONObject data){
        try{
        temp_U = data.optString("temperature");
        speed_U = data.getString("speed");}
        catch (Exception e){}

    }
}
