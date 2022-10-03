package com.ashish.weather_presentation.repository

import com.ashish.core.util.Resource
import com.ashish.weather_domain.model.WeatherData
import com.ashish.weather_domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryFake : WeatherRepository {

    val weatherDataList = (1..25).map {
        println(it)
        WeatherData(
            id = it,
            venueName = "venueName$it",
            countryName = "countryName$it",
            weatherCondition = "weatherCondition$it",
            weatherTemperature = "${it * 3}",
            lastUpdated = "${it * 250}",
            weatherFeelsLike = "weatherFeelsLike$it",
            humidity = "humidity$it",
            wind = "wind$it"
        )
    }

    override suspend fun getWeatherData(fetchFromRemote: Boolean): Flow<Resource<List<WeatherData>>> {
        return flow {
            emit(Resource.Success(weatherDataList))
        }
    }

    override suspend fun getWeatherDataItem(id: Int): Flow<Resource<WeatherData>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFilteredWeatherData(countryId: String): Flow<Resource<List<WeatherData>>> {
        return flow {
            emit(Resource.Success(weatherDataList))
        }
    }
}