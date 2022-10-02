package com.ashish.weather_data.local

import com.ashish.weather_data.local.entity.WeatherDataEntity

class WeatherDaoFake : WeatherDao {

    private var weatherDataList = mutableListOf<WeatherDataEntity>()

    override suspend fun insertWeatherData(weatherData: WeatherDataEntity) {
        weatherDataList.add(weatherData)
    }

    override suspend fun getWeatherDataFromDb(): List<WeatherDataEntity> {
        return weatherDataList
    }

    override suspend fun getWeatherDataItem(id: Int): WeatherDataEntity {
        return weatherDataList[id]
    }

    override suspend fun clearWeatherData() {
        weatherDataList.clear()
    }
}