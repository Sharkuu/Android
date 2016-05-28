package com.example.olaf.weatherproject.data;

import org.json.JSONObject;

/**
 * Created by Olaf on 2016-05-28.
 */
public class Item implements JsonP {
    private Condition condition;
    private Forecast forecast;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data){
        try {
            condition = new Condition();
            condition.populate(data.optJSONObject("condition"));
            forecast = new Forecast();
            forecast.populate(data.optJSONObject("forecast"));
        }catch (Exception e){}
    }
}
