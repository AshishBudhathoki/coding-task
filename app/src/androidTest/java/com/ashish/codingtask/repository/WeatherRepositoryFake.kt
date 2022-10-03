package com.ashish.codingtask.repository

import com.ashish.core.util.Resource
import com.ashish.weather_domain.model.WeatherData
import com.ashish.weather_domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryFake : WeatherRepository {

    private val weatherData = listOf(
        WeatherData(
            venueName = "Dunbogan",
            countryName = "Australia",
            weatherCondition = "Sunny",
            weatherTemperature = "19",
            lastUpdated = "just now",
            weatherFeelsLike = "16",
            humidity = "23",
            wind = "10km/hr",
            id = 12
        )
    )

    override suspend fun getWeatherData(fetchFromRemote: Boolean): Flow<Resource<List<WeatherData>>> {
        return flow {
            emit(Resource.Success(weatherData))
        }
    }

    override suspend fun getWeatherDataItem(id: Int): Flow<Resource<WeatherData>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFilteredWeatherData(countryId: String): Flow<Resource<List<WeatherData>>> {
        return flow {
            emit(Resource.Success(weatherData))
        }
    }
}