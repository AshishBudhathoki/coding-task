package com.ashish.weather_presentation.weather_listings

import com.ashish.weather_domain.usecase.GetWeatherDataUseCase
import com.ashish.weather_presentation.MainCoroutineRule
import com.ashish.weather_presentation.repository.WeatherRepositoryFake
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherListViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: WeatherListViewModel
    private lateinit var repositoryFake: WeatherRepositoryFake
    private lateinit var getWeatherDataUseCase: GetWeatherDataUseCase

    @Before
    fun setUp() {
        repositoryFake = WeatherRepositoryFake()
        getWeatherDataUseCase = GetWeatherDataUseCase(repositoryFake)
        viewModel = WeatherListViewModel(
            getWeatherDataUseCase
        )

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

    }

    @Test
    fun `when viewmodel is created list is populated`() = runTest {

        assertThat(
            viewModel.state.weatherData
        ).isEqualTo(repositoryFake.weatherDataList)

    }

    @Test
    fun `when event is AlphabetSort the list is sorted alphabetically`() = runTest {

        viewModel.onEvent(WeatherListingEvent.AlphabetSort)

        assertThat(
            viewModel.state.weatherData
        ).isEqualTo(repositoryFake.weatherDataList.sortedBy { it.venueName })

    }

    @Test
    fun `when event is Temperature the list is sorted by temperature`() = runTest {

        viewModel.onEvent(WeatherListingEvent.TemperatureSort)

        assertThat(
            viewModel.state.weatherData
        ).isEqualTo(repositoryFake.weatherDataList.sortedBy { it.weatherTemperature?.toLong() })

    }

    @Test
    fun `when event is Last Updated the list is sorted by lastUpdated`() = runTest {

        viewModel.onEvent(WeatherListingEvent.LastUpdatedSort)


        assertThat(
            viewModel.state.weatherData
        ).isEqualTo(repositoryFake.weatherDataList.sortedBy { it.lastUpdated })

    }
}