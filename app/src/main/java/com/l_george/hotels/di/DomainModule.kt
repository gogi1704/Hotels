package com.l_george.hotels.di

import com.l_george.domain2.repository.DomainRepository
import com.l_george.hotels.data.repository.HotelRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun providesDomainRepository(hotelRepository: HotelRepository): DomainRepository =
        DomainRepository(hotelRepository)
}