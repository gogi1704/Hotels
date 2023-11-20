package com.l_george.hotels.di

import android.content.Context
import com.l_george.domain2.repository.DomainRepository
import com.l_george.hotels.viewModels.hotelViewModel.HotelViewModelFactory
import com.l_george.hotels.viewModels.reserveViewModel.ReserveViewModelFactory
import com.l_george.hotels.viewModels.roomViewModel.RoomViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideHotelViewModelFactory(repository: DomainRepository): HotelViewModelFactory {
        return HotelViewModelFactory(repository)
    }


    @Provides
    fun provideRoomViewModelFactory(repository: DomainRepository): RoomViewModelFactory {
        return RoomViewModelFactory(repository)
    }

    @Provides
    fun provideReserveViewModelFactory(repository: DomainRepository): ReserveViewModelFactory {
        return ReserveViewModelFactory(repository)
    }

}