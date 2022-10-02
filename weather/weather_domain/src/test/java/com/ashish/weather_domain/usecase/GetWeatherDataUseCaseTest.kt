package com.ashish.weather_domain.usecase

import com.ashish.weather_domain.repository.WeatherRepositoryFake
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetWeatherDataUseCaseTest {
    private lateinit var getWeatherDataUseCase: GetWeatherDataUseCase
    private lateinit var repositoryFake: WeatherRepositoryFake

    @Before
    fun setUp() {
        repositoryFake = WeatherRepositoryFake()
        getWeatherDataUseCase = GetWeatherDataUseCase(repositoryFake)
    }

    @Test
    fun `given fake repository when getWeatherDataUseCase invoked returns weatherData`() = runTest {
        val expected = 1
        val weatherSize = getWeatherDataUseCase.invoke(true).count()

        assertThat(weatherSize).isEqualTo(expected)

    }
}