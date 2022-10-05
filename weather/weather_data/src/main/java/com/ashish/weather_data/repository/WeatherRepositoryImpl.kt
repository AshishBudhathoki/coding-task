package com.ashish.weather_data.repository

import com.ashish.core.util.Resource
import com.ashish.weather_data.local.WeatherDao
import com.ashish.weather_data.mapper.toDomainWeatherData
import com.ashish.weather_data.mapper.toWeatherDataEntity
import com.ashish.weather_data.remote.WeatherApi
import com.ashish.weather_domain.model.WeatherData
import com.ashish.weather_domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WeatherRepositoryImpl(
    private val dao: WeatherDao,
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(fetchFromRemote: Boolean): Flow<Resource<List<WeatherData>>> {
        return flow {
            emit(Resource.Loading(true))
            val localWeatherData = dao.getWeatherDataFromDb()
            emit(Resource.Success(
                data = localWeatherData.map { it.toDomainWeatherData() }
            ))

            val isDbEmpty = localWeatherData.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteWeatherData = try {
                weatherApi.getWeatherDataFromApi()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteWeatherData?.let { weatherDto ->
                dao.clearWeatherData()
                weatherDto.data.map {
                    dao.insertWeatherData(
                        it.toDomainWeatherData().toWeatherDataEntity()
                    )
                }
                emit(Resource.Success(
                    data = dao.getWeatherDataFromDb().map {
                        it.toDomainWeatherData()
                    }
                ))
                emit(Resource.Loading(false))
            }
        }

    }

    override suspend fun getWeatherDataItem(id: Int): Flow<Resource<WeatherData>> {
        return flow {
            emit(Resource.Loading(true))
            val localWeatherData = dao.getWeatherDataItem(id)
            emit(
                Resource.Success(
                    data = localWeatherData.toDomainWeatherData()
                )
            )
        }
    }

    override suspend fun getFilteredWeatherData(countryId: String): Flow<Resource<List<WeatherData>>> {
        TODO("Not yet implemented")
    }

}