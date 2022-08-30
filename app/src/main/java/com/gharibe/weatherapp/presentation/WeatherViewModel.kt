package com.gahribe.weatherapp.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gahribe.weatherapp.domain.location.LocationTracker
import com.gahribe.weatherapp.domain.repository.WeatherRepository
import com.gahribe.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result = repository.getWeatherData(
                    location.altitude, location.longitude
                )) {
                    is Resource.Success -> {
                        Log.d("Gharibe", "State is")
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null,
                        )
                        Log.d("Gharibe", "State is ${state.weatherInfo?.currentWeatherData?.weatherType?.weatherDesc}")

                    }
                    is Resource.Error -> {
                        Log.d("Gharibe", "Hey failure  ")

                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message,
                        )
                    }
                }
            } ?: kotlin.run {
                Log.d("Gharibe", "location is empty ")
                state = state.copy(
                    isLoading = false,
                    error = "couldn't get location due to unknown error"
                )
            }
        }
    }
}