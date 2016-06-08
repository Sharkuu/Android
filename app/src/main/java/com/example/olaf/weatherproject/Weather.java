package com.example.olaf.weatherproject;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olaf.weatherproject.data.Channel;
import com.example.olaf.weatherproject.data.Location;
import com.example.olaf.weatherproject.service.WeatherCallback;
import com.example.olaf.weatherproject.service.YahooWeather;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;

public class Weather extends AppCompatActivity implements WeatherCallback,LocationListener {
    private ImageView todayWeatherIconView;
    private TextView temperatureText;
    private TextView conditionText;
    private TextView windText;
    private TextView sunsetText;
    private TextView sunriseText;
    private TextView locationText;
    private TextView todayDateText;
    private ImageView nextDayIco;
    private TextView nextDayTemp;
    private TextView nextDayCond;
    private TextView nextDayDate;
    private ImageView nextNextDayIco;
    private TextView nextNextDayTemp;
    private TextView nextNextDayCond;
    private TextView nextNextDayDate;
    private YahooWeather service;
    private ProgressDialog dialog;
    private LocationManager locationManager;
    private Criteria criteria;
    private android.location.Location lc;
    private String bestsupp;
    //private TextView info;

    @Override
    public void onLocationChanged(android.location.Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                Intent viewIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(viewIntent);
                return true;
            case R.id.about:
                Intent intent2 = new Intent(Weather.this, About.class);
                startActivity(intent2);
                return true;

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //info = (TextView)findViewById(R.id.GPS) ;
        todayWeatherIconView = (ImageView)findViewById(R.id.todayWeatherIcon);
        temperatureText = (TextView)findViewById(R.id.todayTemperature);
        conditionText=(TextView)findViewById(R.id.conditions);
        windText = (TextView)findViewById(R.id.wind);
        sunsetText = (TextView)findViewById(R.id.sunset);
        sunriseText = (TextView)findViewById(R.id.sunrise);
        locationText= (TextView)findViewById(R.id.location);
        todayDateText = (TextView)findViewById(R.id.dateToday);
        nextDayIco = (ImageView)findViewById(R.id.nextDayIco);
        nextDayTemp = (TextView)findViewById(R.id.nextDayTemp);
        nextDayCond = (TextView)findViewById(R.id.nextConditions);
        nextDayDate = (TextView)findViewById(R.id.nextdate) ;
        nextNextDayIco = (ImageView)findViewById(R.id.nextnextDayIco);
        nextNextDayTemp = (TextView)findViewById(R.id.nextNextTemp);
        nextNextDayCond = (TextView)findViewById(R.id.nextNextCondition);
        nextNextDayDate = (TextView)findViewById(R.id.nextnextDate) ;

        service = new YahooWeather(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        //////Geo
        criteria = new Criteria();
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        bestsupp = GPS_PROVIDER;
        lc = locationManager.getLastKnownLocation(bestsupp);
        if(lc == null){
            bestsupp = NETWORK_PROVIDER;
            lc = locationManager.getLastKnownLocation(bestsupp);
        }
        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocation(lc.getLatitude(), lc.getLongitude(), 1);
           service.refreashWeather(addresses.get(0).getLocality()+","+ addresses.get(0).getCountryName());
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

        //////
       // service.refreashWeather("Tokyo, Japan");

    }

    @Override
    public void serviceSuccess(Channel channel) {
    dialog.hide();
        int source = getResources().getIdentifier("drawable/icon_"+channel.getItem().getCondition().getCode(),null, getPackageName());
        @SuppressWarnings("deprecation")
        Drawable todayWeatherIcon = getResources().getDrawable(source);
        todayWeatherIconView.setImageDrawable(todayWeatherIcon);
        locationText.setText(service.getLocation());
        temperatureText.setText(channel.getItem().getCondition().getTemperature()+ " "+(char) 0x00B0+ channel.getUnits().getTemp());
        windText.setText("Wind: "+channel.getWind().getWind_speed()+" "+channel.getUnits().getSpeed());
        conditionText.setText(channel.getItem().getCondition().getText());
        sunriseText.setText(channel.getAstronomy().getSunrise());
        sunsetText.setText(channel.getAstronomy().getSunset());
        todayDateText.setText(channel.getItem().getCondition().getDate());
        source = getResources().getIdentifier("drawable/icon_"+channel.getItem().getForecast1().getCode(),null, getPackageName());
        @SuppressWarnings("deprecation")
        Drawable nextWeatherIcon = getResources().getDrawable(source);
        nextDayIco.setImageDrawable(nextWeatherIcon);
        nextDayTemp.setText(channel.getItem().getForecast1().getAvg()+""+(char) 0x00B0+channel.getUnits().getTemp());
        nextDayCond.setText(channel.getItem().getForecast1().getText());
        nextDayDate.setText(channel.getItem().getForecast1().getDate());

        source = getResources().getIdentifier("drawable/icon_"+channel.getItem().getForecast2().getCode(),null, getPackageName());
        @SuppressWarnings("deprecation")
        Drawable nextNextWeatherIcon = getResources().getDrawable(source);
        nextNextDayIco.setImageDrawable(nextNextWeatherIcon);
        nextNextDayTemp.setText(channel.getItem().getForecast2().getAvg()+""+(char) 0x00B0+channel.getUnits().getTemp());
        nextNextDayCond.setText(channel.getItem().getForecast2().getText());
        nextNextDayDate.setText(channel.getItem().getForecast2().getDate());

    }
    @Override
    public void serviceFail(Exception e) {
        dialog.hide();
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
    }
}
