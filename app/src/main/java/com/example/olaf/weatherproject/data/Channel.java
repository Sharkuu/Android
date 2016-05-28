package com.example.olaf.weatherproject.data;

import org.json.JSONObject;

/**
 * Created by Olaf on 2016-05-28.
 */
public class Channel implements JsonP {

    private Location location;
    private Item item;
    private Units units;
    private Astronomy astronomy;
    private Wind wind;

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public Item getItem() {
        return item;
    }

    public Location getLocation() {
        return location;
    }

    public Units getUnits() {
        return units;
    }

    public Wind getWind() {
        return wind;
    }
    @Override
    public void populate(JSONObject data){
        try{
        units = new Units();
        units.populate(data.optJSONObject("units"));

        location = new Location();
        location.populate(data.optJSONObject("location"));

        item = new Item();
        item.populate(data.optJSONObject("item"));
        astronomy = new Astronomy();
        astronomy.populate(data.optJSONObject("astronomy"));

        wind = new Wind();
        wind.populate(data.optJSONObject("wind"));
        }catch (Exception e){}
    }

}
