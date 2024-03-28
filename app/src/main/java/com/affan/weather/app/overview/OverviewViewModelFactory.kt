package com.affan.weather.app.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.affan.weather.app.repository.Repository

class OverviewViewModelFactory(val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverViewViewModel::class.java)) {
            return OverViewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}