package com.ashish.weather_presentation.weather_info

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.core.util.Resource
import com.ashish.weather_domain.usecase.GetWeatherItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * ViewModel to display selected weather info
 */
@HiltViewModel
class WeatherInfoViewModel @Inject constructor(
    private val getWeatherItemUseCase: GetWeatherItemUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val argument = checkNotNull(savedStateHandle.get<String>("id"))
    var state by mutableStateOf(WeatherInfoState())

    init {
        getWeatherItem(argument.toInt())
    }

    private fun getWeatherItem(id: Int) {
        viewModelScope.launch {
            getWeatherItemUseCase.invoke(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { weatherData ->
                            state = state.copy(
                                weatherData = weatherData
                            )
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

    fun onEvent(weatherItemEvent: WeatherInfoEvent) {
        when (weatherItemEvent) {
            is WeatherInfoEvent.Close -> {
                Log.d("WeatherITEM", "Closed")
            }
        }
    }
}