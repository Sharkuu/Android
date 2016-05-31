package com.example.olaf.weatherproject.data;

import org.json.JSONObject;

/**
 * Created by Olaf on 2016-05-28.
 */
public class Item implements JsonP {
    private Condition condition;
    private Forecast forecast1;
    private Forecast forecast2;

    public Forecast getForecast1() {
        return forecast1;
    }

    public Forecast getForecast2() {
        return forecast2;
    }

    public Condition getCondition() {
        return condition;
    }



    @Override
    public void populate(JSONObject data){
        try {
            condition = new Condition();
            condition.populate(data.optJSONObject("condition"));
            forecast1 = new Forecast();
            forecast1.populate(data.optJSONArray("forecast"),1);
            forecast2 = new Forecast();
            forecast2.populate(data.optJSONArray("forecast"),2);

        }catch (Exception e){}
    }
}
