package com.gahribe.weatherapp.domain.repository

import com.gahribe.weatherapp.domain.util.Resource
import com.gahribe.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}