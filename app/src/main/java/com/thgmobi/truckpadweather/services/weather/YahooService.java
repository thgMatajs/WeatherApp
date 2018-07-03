package com.thgmobi.truckpadweather.services.weather;

import com.thgmobi.truckpadweather.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YahooService {

    String BASE_URL = "http://query.yahooapis.com/";

    @GET("v1/public/yql")
    Call<Weather> getWeather(@Query("q") String query,
                             @Query("format") String format);

}
