package com.ashish.weather_data.repository

import app.cash.turbine.test
import com.ashish.core.util.Resource
import com.ashish.weather_data.local.WeatherDao
import com.ashish.weather_data.local.WeatherDaoFake
import com.ashish.weather_data.local.WeatherDatabase
import com.ashish.weather_data.local.entity.WeatherDataEntity
import com.ashish.weather_data.mapper.toDomainWeatherData
import com.ashish.weather_data.remote.WeatherApi
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {

    private val weatherDataList = (1..25).map {
        WeatherDataEntity(
            id = it,
            venueName = "venueName$it",
            countryName = "countryName$it",
            weatherCondition = "weatherCondition$it",
            weatherTemperature = "weatherTemperature$it",
            lastUpdated = "lastUpdated:$it",
            weatherFeelsLike = "weatherFeelsLike$it",
            humidity = "humidity$it",
            wind = "wind$it"
        )
    }

    private lateinit var repository: WeatherRepositoryImpl
    private lateinit var api: WeatherApi
    private lateinit var db: WeatherDatabase
    private lateinit var weatherDao: WeatherDao


    @Before
    fun setUp() {
        api = mockk(relaxed = true) {
            coEvery { getWeatherDataFromApi() } returns mockk(relaxed = true)
        }

        weatherDao = WeatherDaoFake()
        db = mockk(relaxed = true) {
            every { dao } returns weatherDao
        }

        repository = WeatherRepositoryImpl(
            weatherApi = api,
            dao = weatherDao
        )

    }

    @Test
    fun `Test local database cache with fetch from remote set to true`() = runTest {

        weatherDataList.forEach {
            weatherDao.insertWeatherData(it)
        }

        repository.getWeatherData(true).test {
            val startLoading = awaitItem()
            assertThat((startLoading as Resource.Loading).isLoading).isTrue()

            val weatherDataFromDb = awaitItem()
            assertThat(weatherDataFromDb is Resource.Success).isTrue()
            assertThat(weatherDataFromDb.data).isEqualTo(weatherDataList.map { it.toDomainWeatherData() })

            val remoteWeatherDataFromDb = awaitItem()
            assertThat(remoteWeatherDataFromDb is Resource.Success).isTrue()
            assertThat(remoteWeatherDataFromDb.data).isEqualTo(
                weatherDao.getWeatherDataFromDb().map { it.toDomainWeatherData() }
            )

            val stopLoading = awaitItem()
            assertThat((stopLoading as Resource.Loading).isLoading).isFalse()

            awaitComplete()
        }
    }
}