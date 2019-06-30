package com.universodoandroid.smartbuilding.remote.api

import com.universodoandroid.smartbuilding.BuildConfig
import com.universodoandroid.smartbuilding.remote.services.ApartmentApiDataSource
import com.universodoandroid.smartbuilding.remote.services.AuthApiDataSource

object InjectionApiDataSourceMain {

    private fun provideApiDataSource(): ApiDataSource {
        val baseUrl = BuildConfig.BASE_URL
        return ApiDataSource.getInstance(baseUrl)
    }

    fun provideAuthApiDataSource(): AuthApiDataSource {
        return provideApiDataSource().createService(AuthApiDataSource::class.java)
    }

    fun provideSensorApiDataSource(): ApartmentApiDataSource {
        return provideApiDataSource().createService(ApartmentApiDataSource::class.java)
    }

    fun provideApartmentApiDataSource(): ApartmentApiDataSource {
        return provideApiDataSource().createService(ApartmentApiDataSource::class.java)
    }

}