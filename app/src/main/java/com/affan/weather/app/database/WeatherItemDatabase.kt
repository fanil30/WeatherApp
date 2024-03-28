package com.affan.weather.app.database

import android.content.Context
import androidx.room.*
import com.affan.weather.app.converters.CityTypeConverter
import com.affan.weather.app.converters.CoordTypeConverter
import com.affan.weather.app.converters.MainTypeConverter
import com.affan.weather.app.converters.WeatherDataConverter
import com.affan.weather.app.converters.WeatherItemLstTypeConverter
import com.affan.weather.app.converters.WeatherLstTypeConverter
import com.affan.weather.app.converters.WeatherTypeConverter
import com.affan.weather.app.model.WeatherData


@Database(
    entities = [WeatherData::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(WeatherItemLstTypeConverter::class, MainTypeConverter::class, WeatherTypeConverter::class, WeatherLstTypeConverter::class, CityTypeConverter::class, CoordTypeConverter::class, WeatherDataConverter::class)
abstract class WeatherItemDatabase : RoomDatabase() {
    abstract val weatherItemDatabaseDao: WeatherItemDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherItemDatabase? = null

        fun getDatabase(context: Context): WeatherItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherItemDatabase::class.java,
                    "open_weather_app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}