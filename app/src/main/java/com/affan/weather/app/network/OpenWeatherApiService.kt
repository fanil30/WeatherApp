package com.affan.weather.app.network

import com.affan.weather.app.model.WeatherData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface OpenWeatherApiService {
    @GET("data/2.5/forecast?lat=33.5651&lon=73.0169&appid=1f55634e30ce27a7f583832125efa857")
    fun getFiveDayWeatherForecast(): Deferred<WeatherData>
}