package com.ashish.weather_domain.model

data class WeatherData(
    val id: Int? = null,
    val venueName: String,
    val countryName: String?,
    val weatherCondition: String?,
    val weatherTemperature: String?,
    val lastUpdated: String?
)