package com.example.olaf.weatherproject.service;

import com.example.olaf.weatherproject.data.Channel;

/**
 * Created by Olaf on 2016-05-28.
 */
public interface WeatherCallback {
    void serviceSuccess(Channel channel);
    void serviceFail(Exception e);

}
