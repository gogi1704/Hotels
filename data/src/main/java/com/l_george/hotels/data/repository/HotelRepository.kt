package com.l_george.hotels.data.repository

import com.l_george.hotels.domain.models.hotelModel.HotelModel
import com.l_george.hotels.domain.models.roomModel.RoomModel

interface HotelRepository {
   suspend fun getHotel(): HotelModel
   suspend fun getRooms():List<RoomModel>
}