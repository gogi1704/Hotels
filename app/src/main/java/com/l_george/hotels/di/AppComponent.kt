package com.l_george.hotels.di

import com.l_george.hotels.MainActivity
import com.l_george.hotels.ui.fragments.HotelsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class , ApiModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(hotelFragment:HotelsFragment)
}