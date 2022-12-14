package com.ashish.weather_domain.di

import com.ashish.weather_domain.repository.WeatherRepository
import com.ashish.weather_domain.usecase.GetWeatherDataUseCase
import com.ashish.weather_domain.usecase.GetWeatherItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/***
 * Dependencies for usecases from domain
 */
@Module
@InstallIn(ViewModelComponent::class)
object WeatherDomainUseCase {

    @ViewModelScoped
    @Provides
    fun provideGerWeatherDataUseCase(
        repository: WeatherRepository
    ): GetWeatherDataUseCase {
        return GetWeatherDataUseCase(repository)
    }

    @ViewModelScoped
    @Provides
    fun provideWeatherDataItemUseCase(
        repository: WeatherRepository
    ): GetWeatherItemUseCase {
        return GetWeatherItemUseCase(repository)
    }
}