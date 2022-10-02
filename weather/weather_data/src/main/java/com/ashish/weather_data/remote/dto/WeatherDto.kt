package com.ashish.weather_data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDto(
    val ret: Boolean? = null,
    val isOkay: Boolean? = null,
    val data: List<Data>
)
