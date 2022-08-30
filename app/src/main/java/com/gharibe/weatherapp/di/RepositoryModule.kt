package com.gahribe.weatherapp.di

import com.gahribe.weatherapp.data.repository.WeatherRepositoryImpl
import com.gahribe.weatherapp.domain.location.LocationTracker
import com.gahribe.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ) : WeatherRepository
}