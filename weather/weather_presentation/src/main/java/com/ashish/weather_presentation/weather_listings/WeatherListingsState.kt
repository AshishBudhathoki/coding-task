package com.ashish.weather_presentation.weather_listings

import com.ashish.weather_domain.model.WeatherData

data class WeatherListingsState(
    val weatherData: List<WeatherData> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)
