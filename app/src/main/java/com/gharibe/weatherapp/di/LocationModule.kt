package com.gahribe.weatherapp.di

import com.gahribe.weatherapp.data.Location.DefaultLocationTracker
import com.gahribe.weatherapp.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTracker : DefaultLocationTracker) : LocationTracker
}