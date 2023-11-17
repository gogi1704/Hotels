package com.l_george.hotels.di

import com.l_george.hotels.apiService.api.ApiService
import com.l_george.hotels.data.repository.HotelRepository
import com.l_george.hotels.data.repository.HotelRepositoryImpl
import dagger.Module
import dagger.Provides


@Module
class DataModule {

    @Provides
    fun providesRepository(apiService: ApiService): HotelRepository = HotelRepositoryImpl(apiService)


}