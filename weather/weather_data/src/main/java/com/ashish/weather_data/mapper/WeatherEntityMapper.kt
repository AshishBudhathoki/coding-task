package com.ashish.weather_data.mapper

import com.ashish.weather_data.local.entity.WeatherDataEntity
import com.ashish.weather_domain.model.WeatherData


fun WeatherData.toWeatherDataEntity(): WeatherDataEntity {
    return WeatherDataEntity(
        id = id,
        venueName = venueName,
        countryName = countryName,
        weatherCondition = weatherCondition,
        weatherTemperature = weatherTemperature,
        lastUpdated = lastUpdated,
        humidity = humidity,
        weatherFeelsLike = weatherFeelsLike,
        wind = wind
    )
}

fun WeatherDataEntity.toDomainWeatherData(): WeatherData {
    return WeatherData(
        id = id,
        venueName = venueName,
        countryName = countryName,
        weatherCondition = weatherCondition,
        weatherTemperature = weatherTemperature,
        lastUpdated = lastUpdated,
        humidity = humidity,
        weatherFeelsLike = weatherFeelsLike,
        wind = wind
    )
}