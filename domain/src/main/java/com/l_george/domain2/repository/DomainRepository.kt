package com.l_george.domain2.repository

import com.l_george.hotels.data.repository.HotelRepository
import com.l_george.hotels.domain.models.hotelModel.HotelModel
import com.l_george.hotels.domain.models.reserveModel.ReserveModel
import com.l_george.hotels.domain.models.roomModel.RoomModel

class DomainRepository(private val hotelRepository: HotelRepository) {
    suspend fun getHotel(): HotelModel = hotelRepository.getHotel()
    suspend fun getRooms(): List<RoomModel> = hotelRepository.getRooms()
    suspend fun getHotelReserved(): ReserveModel = hotelRepository.getHotelReserved()
}