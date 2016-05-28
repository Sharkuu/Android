package com.example.olaf.weatherproject.data;

import org.json.JSONObject;

/**
 * Created by Olaf on 2016-05-28.
 */
public class Location implements JsonP {
    private String location_city;
    private String location_country;

    public String getLocation_city() {
        return location_city;
    }

    public String getLocation_country() {
        return location_country;
    }


    @Override
    public void populate(JSONObject data) {
        try{
    location_city = data.optString("city");
        location_country = data.optString("country");
        }catch (Exception e){}
    }
}
