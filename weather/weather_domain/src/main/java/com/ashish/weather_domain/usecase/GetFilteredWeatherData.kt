package com.ashish.weather_domain.usecase

import com.ashish.core.util.Resource
import com.ashish.weather_domain.model.WeatherData
import com.ashish.weather_domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetFilteredWeatherData(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(countryId: String): Flow<Resource<List<WeatherData>>> {
        return repository.getFilteredWeatherData(countryId)
    }
}