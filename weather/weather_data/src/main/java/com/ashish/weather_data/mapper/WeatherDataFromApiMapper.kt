package com.ashish.weather_data.mapper

import com.ashish.weather_data.remote.dto.Data
import com.ashish.weather_domain.model.WeatherData

fun Data.toDomainWeatherData(): WeatherData {
    return WeatherData(
        venueName = name,
        countryName = country?.name,
        weatherCondition = weatherCondition,
        weatherTemperature = weatherTemp,
        lastUpdated = weatherLastUpdated.toString(),
        weatherFeelsLike = weatherFeelsLike,
        humidity = weatherHumidity,
        wind = weatherWind
    )
}
