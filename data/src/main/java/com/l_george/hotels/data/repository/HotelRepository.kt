package com.l_george.hotels.data.repository

import com.l_george.hotels.domain.models.hotelModel.HotelModel

interface HotelRepository {
   suspend fun getHotel(): HotelModel
}