package com.affan.weather.app.overview

import androidx.lifecycle.*
import com.affan.weather.app.model.WeatherData
import com.affan.weather.app.repository.OpenWeatherAppApiStatus
import com.affan.weather.app.repository.OpenWeatherRepository
import kotlinx.coroutines.launch


class OverViewViewModel(val repository: OpenWeatherRepository) : ViewModel() {
    val weatherData = getWeatherDataFromDatasource()
    val status = getApiStatus()

    init {
        refreshCache()
    }

    fun refreshCache() {
        viewModelScope.launch { repository.refreshCache() }
    }
    fun getWeatherDataFromDatasource():LiveData<List<WeatherData>> = repository.getWeatherData()
    fun getApiStatus(): LiveData<OpenWeatherAppApiStatus> = repository.getApiStatus()

}