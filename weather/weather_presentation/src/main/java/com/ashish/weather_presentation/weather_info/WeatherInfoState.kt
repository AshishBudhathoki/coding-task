package com.ashish.weather_presentation.weather_info

import com.ashish.weather_domain.model.WeatherData

/***
 * data states of weather info screen
 */
data class WeatherInfoState(
    val weatherData: WeatherData? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null

)