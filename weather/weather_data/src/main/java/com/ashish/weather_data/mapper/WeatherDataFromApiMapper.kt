package com.ashish.weather_data.mapper

import com.ashish.core.util.formatHumidityValue
import com.ashish.core.util.formatWindValue
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
        humidity = weatherHumidity.formatHumidityValue(),
        wind = weatherWind.formatWindValue()
    )
}
