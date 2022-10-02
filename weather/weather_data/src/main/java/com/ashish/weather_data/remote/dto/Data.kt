package com.ashish.weather_data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "_venueID")
    val venueID: String?,
    @Json(name = "_name")
    val name: String,
    @Json(name = "_country")
    val country: Country? = Country(),
    @Json(name = "_weatherCondition")
    val weatherCondition: String?,
    @Json(name = "_weatherWind")
    val weatherWind: String? = null,
    @Json(name = "_weatherHumidity")
    val weatherHumidity: String? = null,
    @Json(name = "_weatherTemp")
    val weatherTemp: String?,
    @Json(name = "_weatherFeelsLike")
    val weatherFeelsLike: String? = null,
    @Json(name = "_weatherLastUpdated")
    val weatherLastUpdated: Long?
)
