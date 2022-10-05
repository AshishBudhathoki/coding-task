package com.ashish.weather_presentation.weather_listings

sealed class WeatherListingEvent {
    object Refresh : WeatherListingEvent()
    object AlphabetSort : WeatherListingEvent()
    object TemperatureSort : WeatherListingEvent()
    object LastUpdatedSort : WeatherListingEvent()
    object CountryFilter : WeatherListingEvent()
}