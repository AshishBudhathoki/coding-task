package com.ashish.weather_domain.repository

import com.ashish.core.util.Resource
import com.ashish.weather_domain.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherData(fetchFromRemote: Boolean): Flow<Resource<List<WeatherData>>>

    suspend fun getWeatherDataItem(id: Int): Flow<Resource<WeatherData>>

    suspend fun getFilteredWeatherData(countryId: String): Flow<Resource<List<WeatherData>>>
}