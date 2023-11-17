package com.l_george.hotels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.l_george.hotels.app.HotelApp


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as HotelApp).appComponent.inject(this)

        setContentView(R.layout.activity_main)
    }
}