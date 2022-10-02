package com.ashish.weather_domain.usecase

import com.ashish.core.util.Resource
import com.ashish.weather_domain.model.WeatherData
import com.ashish.weather_domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherItemUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<WeatherData>> {
        return repository.getWeatherDataItem(id)
    }
}