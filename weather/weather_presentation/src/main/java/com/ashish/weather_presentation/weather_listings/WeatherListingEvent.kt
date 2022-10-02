package com.ashish.weather_presentation.weather_listings

import com.ashish.weather_domain.model.WeatherData

sealed class WeatherListingEvent {
    object Refresh : WeatherListingEvent()
    object AlphabetSort : WeatherListingEvent()
    object TemperatureSort : WeatherListingEvent()
    object LastUpdatedSort : WeatherListingEvent()
    object CountryFilter : WeatherListingEvent()
}