package com.example.olaf.weatherproject;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olaf.weatherproject.data.Channel;
import com.example.olaf.weatherproject.service.WeatherCallback;
import com.example.olaf.weatherproject.service.YahooWeather;

public class Weather extends AppCompatActivity implements WeatherCallback {
    private ImageView todayWeatherIconView;
    private TextView temperatureText;
    private TextView conditionText;
    private TextView windText;
    private TextView sunsetText;
    private TextView sunriseText;
    private TextView locationText;
    private TextView todayDateText;
    private YahooWeather service;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        todayWeatherIconView = (ImageView)findViewById(R.id.todayWeatherIcon);
        temperatureText = (TextView)findViewById(R.id.todayTemperature);
        conditionText=(TextView)findViewById(R.id.conditions);
        windText = (TextView)findViewById(R.id.wind);
        sunsetText = (TextView)findViewById(R.id.sunset);
        sunriseText = (TextView)findViewById(R.id.sunrise);
        locationText= (TextView)findViewById(R.id.location);
        todayDateText = (TextView)findViewById(R.id.dateToday);

        service = new YahooWeather(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreashWeather("Cracow, Poland");

    }

    @Override
    public void serviceSuccess(Channel channel) {
    dialog.hide();
        int source = getResources().getIdentifier("drawable/icon_"+channel.getItem().getCondition().getCode(),null, getPackageName());
        @SuppressWarnings("deprecation")
        Drawable todayWeatherIcon = getResources().getDrawable(source);
        todayWeatherIconView.setImageDrawable(todayWeatherIcon);

        locationText.setText(service.getLocation());
        temperatureText.setText(channel.getItem().getCondition().getTemperature()+ " "+channel.getUnits().getTemp());
        windText.setText(channel.getWind().getWind_speed()+" "+channel.getUnits().getSpeed());
        conditionText.setText(channel.getItem().getCondition().getText());
        sunriseText.setText(channel.getAstronomy().getSunrise());
        sunsetText.setText(channel.getAstronomy().getSunset());
        todayDateText.setText(channel.getItem().getCondition().getDate());

    }

    @Override
    public void serviceFail(Exception e) {
        dialog.hide();
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
    }
}
