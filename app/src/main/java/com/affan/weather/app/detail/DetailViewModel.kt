package com.affan.weather.app.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.affan.weather.app.model.WeatherItem

class DetailViewModel(val item: WeatherItem): ViewModel() {
    private val _weatherItem = MutableLiveData(item)
    val weatherItem: LiveData<WeatherItem> get() = _weatherItem
}