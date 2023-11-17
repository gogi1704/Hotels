package com.l_george.hotels.di

import android.content.Context
import com.l_george.hotels.data.repository.HotelRepository
import com.l_george.hotels.viewModels.HotelViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext():Context = context

    @Provides
    fun provideHotelViewModelFactory(repository: HotelRepository): HotelViewModelFactory {
        return HotelViewModelFactory(repository)
    }

}