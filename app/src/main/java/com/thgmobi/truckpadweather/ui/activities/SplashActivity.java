package com.thgmobi.truckpadweather.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.thgmobi.truckpadweather.R;
import com.thgmobi.truckpadweather.models.Weather;
import com.thgmobi.truckpadweather.models.WeatherResult;
import com.thgmobi.truckpadweather.services.weather.Factory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    WeatherResult weatherResult = new WeatherResult();
    private FusedLocationProviderClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        client = LocationServices.getFusedLocationProviderClient(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPermissions();
            }
        }, 5000);


    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            configServiceLocation();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configServiceLocation();
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


    private void configServiceLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        client.getLastLocation().addOnSuccessListener(SplashActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                getDeviceLocation(location);
            }
        });

    }


    private void getDeviceLocation(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        getWeather(latitude, longitude);

    }

    public void getWeather(double latitude, double longitude) {

        String YQL = String.format(
                "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"("
                        + latitude + "," + longitude + ")\") AND u='c'");

        Factory.getInstance().getWeather(YQL, "json").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                try {
                    weatherResult.setTemp(response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp());
                    weatherResult.setCity(response.body().getQuery().getResults().getChannel().getLocation().getCity());
                    weatherResult.setRegion(response.body().getQuery().getResults().getChannel().getLocation().getRegion());

                    weatherResult.setCondition(response.body().getQuery().getResults().getChannel().getItem().getCondition().getText());
                    weatherResult.setConditionCode(Integer.parseInt(response.body().getQuery().getResults().getChannel().getItem().getCondition().getCode()));

                    Intent intent = new Intent(SplashActivity.this, WeatherActivity.class);
                    intent.putExtra("clima", weatherResult);
                    startActivity(intent);

                    finish();


                } catch (Exception e) {
                    e.getMessage();
                }

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

    }


}
