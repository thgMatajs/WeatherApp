package com.thgmobi.truckpadweather.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.thgmobi.truckpadweather.R;
import com.thgmobi.truckpadweather.helper.ChangeIconHelper;
import com.thgmobi.truckpadweather.models.WeatherResult;

public class WeatherActivity extends AppCompatActivity {

    private TextView tvCity;
    private TextView tvTemp;
    private TextView tvCondition;
    private ImageView ivCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        initVars();
        receiveWeatherResult();

    }

    private void initVars() {
        tvCity = findViewById(R.id.weather_tv_cidade);
        tvTemp = findViewById(R.id.weather_tv_temp);
        tvCondition = findViewById(R.id.weather_tv_condicao);
        ivCondition = findViewById(R.id.weather_iv_condicao);

    }

    private void receiveWeatherResult() {
        final Intent intent = getIntent();
        WeatherResult weatherResult = (WeatherResult) intent.getSerializableExtra("clima");

        String cityAndRegion = weatherResult.getCity() + " - " + weatherResult.getRegion();
        tvCity.setText(cityAndRegion);

        String temp = weatherResult.getTemp() + "°C";
        tvTemp.setText(temp);
        tvCondition.setText(weatherResult.getCondition());

        ChangeIconHelper.changeIconWeather(ivCondition, String.valueOf(weatherResult.getConditionCode()));
    }


}
