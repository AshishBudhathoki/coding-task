package com.ashish.weather_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherDataEntity(
    @PrimaryKey val id: Int? = null,
    val venueName: String,
    val countryName: String?,
    val weatherCondition: String?,
    val weatherTemperature: String?,
    val lastUpdated: String?,
    val weatherFeelsLike: String?,
    val humidity: String?,
    val wind: String?
)
