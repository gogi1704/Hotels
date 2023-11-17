package com.l_george.hotels.app

import android.app.Application
import com.l_george.hotels.di.AppComponent
import com.l_george.hotels.di.AppModule
import com.l_george.hotels.di.DaggerAppComponent

class HotelApp : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        super.onCreate()
    }
}