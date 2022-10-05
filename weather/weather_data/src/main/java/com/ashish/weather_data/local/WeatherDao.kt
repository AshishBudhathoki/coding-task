package com.ashish.weather_data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashish.weather_data.local.entity.WeatherDataEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(weatherData: WeatherDataEntity)

    @Query(
        """
            SELECT *
            FROM weatherdataentity
        """
    )
    suspend fun getWeatherDataFromDb(): List<WeatherDataEntity>


    @Query(
        """
            SELECT *
            FROM weatherdataentity
            WHERE id = :id 
        """
    )
    suspend fun getWeatherDataItem(id: Int): WeatherDataEntity

    @Query(
        """
            DELETE 
            FROM 
            weatherdataentity
        """
    )
    suspend fun clearWeatherData()
}