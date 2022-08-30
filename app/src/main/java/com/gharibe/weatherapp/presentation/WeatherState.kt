package com.gahribe.weatherapp.presentation

import com.gahribe.weatherapp.domain.util.Resource
import com.gahribe.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)