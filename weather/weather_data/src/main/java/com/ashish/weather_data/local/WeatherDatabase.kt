package com.ashish.weather_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashish.weather_data.local.entity.WeatherDataEntity

@Database(
    entities = [WeatherDataEntity::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val dao: WeatherDao
}