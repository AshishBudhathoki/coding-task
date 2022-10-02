package com.ashish.weather_data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "_countryID")
    val countryID: String? = null,
    @Json(name = "_name")
    var name: String = ""
)
