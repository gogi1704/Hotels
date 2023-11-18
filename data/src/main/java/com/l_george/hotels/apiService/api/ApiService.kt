package com.l_george.hotels.apiService.api

import com.l_george.hotels.domain.models.reserveModel.ReserveModel
import com.l_george.hotels.domain.models.hotelModel.HotelModel
import com.l_george.hotels.domain.models.roomModel.RoomsFromApi
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v3/d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): Response<HotelModel>

    @GET("v3/8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms(): Response<RoomsFromApi>

    @GET("v3/63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getHotelToReserve(): Response<ReserveModel>


}