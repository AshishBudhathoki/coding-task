package com.ashish.weather_data.remote

import com.ashish.weather_data.remote.dto.WeatherDto
import retrofit2.http.GET

interface WeatherApi {

    @GET("venues/weather.json")
    suspend fun getWeatherDataFromApi(): WeatherDto

    companion object {
        const val BASE_URL = "https://dnu5embx6omws.cloudfront.net/"
    }
}