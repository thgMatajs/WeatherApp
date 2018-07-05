package com.thgmobi.truckpadweather.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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
    LocationManager locationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        configSplash();
    }

    private void configSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPermissions();
            }
        }, 1000);
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            checkStatusGps();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkStatusGps();
                } else {
                    Toast.makeText(this, "É preciso ter acesso ao GPS para o app funcionar", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void checkStatusGps() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        boolean isEnbled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!isEnbled) {
            Toast.makeText(this, "Por favor, ative o GPS para que o App funcione corretamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);

        } else {
            configServiceLocation();
        }


    }

    private void configServiceLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, servicoLocalizacao);

    }

    private LocationListener servicoLocalizacao = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            getDeviceLocation(location);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void getDeviceLocation(Location location) {


        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        locationManager.removeUpdates(servicoLocalizacao);

        getWeather(latitude, longitude);
    }

    public void getWeather(double latitude, double longitude) {

        String YQL = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"("
                + latitude + "," + longitude + ")\") AND u='c'";

        Factory.getInstance().getWeather(YQL, "json").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {

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
            public void onFailure(@NonNull Call<Weather> call, Throwable t) {

            }
        });

    }


}
