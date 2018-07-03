package com.thgmobi.truckpadweather.services.weather;

import com.thgmobi.truckpadweather.models.Weather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Factory implements YahooService{

    private static YahooService yahooService;

    public static YahooService getInstance() {
        if (yahooService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
            yahooService =retrofit.create(YahooService.class);
            return yahooService;
        } else {
            return yahooService;
        }
    }

    @Override
    public Call<Weather> getWeather(String query, String format) {
        return null;
    }
}
