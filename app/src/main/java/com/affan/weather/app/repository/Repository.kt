package com.affan.weather.app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.affan.weather.app.database.WeatherItemDatabase
import com.affan.weather.app.model.WeatherData
import com.affan.weather.app.network.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

enum class OpenWeatherAppApiStatus { LOADING, ERROR, DONE }

interface OpenWeatherRepository {
    fun getApiStatus(): LiveData<OpenWeatherAppApiStatus>
    fun getWeatherData(): LiveData<List<WeatherData>>
    suspend fun refreshCache()
}

class Repository(val database: WeatherItemDatabase) : OpenWeatherRepository {

    private val _status = MutableLiveData(OpenWeatherAppApiStatus.LOADING)
    private val status: LiveData<OpenWeatherAppApiStatus> get() = _status

    override fun getApiStatus(): LiveData<OpenWeatherAppApiStatus> = status

    override fun getWeatherData(): LiveData<List<WeatherData>> =
        database.weatherItemDatabaseDao.getWeatherData()

    override suspend fun refreshCache() {
        val data = withContext(Dispatchers.IO) {
            val weatherData = Client.getDailyForecast()
            weatherData?.let { database.weatherItemDatabaseDao.insert(weatherData) }
            weatherData
        } ?: null

        _status.value = OpenWeatherAppApiStatus.DONE
        if (data == null) _status.value = OpenWeatherAppApiStatus.ERROR
    }
}


