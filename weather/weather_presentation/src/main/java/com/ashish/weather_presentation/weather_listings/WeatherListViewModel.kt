package com.ashish.weather_presentation.weather_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.core.util.Resource
import com.ashish.weather_domain.usecase.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase
) : ViewModel() {

    var state by mutableStateOf(WeatherListingsState())

    init {
        getWeatherData()
    }


    fun onEvent(event: WeatherListingEvent) {
        when (event) {
            is WeatherListingEvent.Refresh -> {
                getWeatherData(true)
            }
            is WeatherListingEvent.AlphabetSort -> {
                state = state.copy(
                    weatherData = state.weatherData.sortedBy { it.venueName },
                    tabPosition = 0
                )
            }
            is WeatherListingEvent.TemperatureSort -> {
                state = state.copy(
                    weatherData = state.weatherData.filter { !it.weatherTemperature.isNullOrEmpty() }
                        .sortedBy { it.weatherTemperature?.toInt() },
                    tabPosition = 1
                )
            }
            is WeatherListingEvent.LastUpdatedSort -> {
                state = state.copy(
                    weatherData = state.weatherData.filter { !it.lastUpdated.isNullOrEmpty() }
                        .sortedBy { it.lastUpdated },
                    tabPosition = 2
                )
            }
        }

    }

    private fun getWeatherData(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            getWeatherDataUseCase.invoke(fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { weatherData ->
                            state = state.copy(
                                weatherData = weatherData
                            )
                            when (state.tabPosition) {
                                0 -> {
                                    onEvent(WeatherListingEvent.AlphabetSort)
                                }
                                1 -> {
                                    onEvent(WeatherListingEvent.TemperatureSort)
                                }
                                2 -> {
                                    onEvent(WeatherListingEvent.LastUpdatedSort)
                                }
                                else -> {}
                            }
                        }
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }

        }
    }
}