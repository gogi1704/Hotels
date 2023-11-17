package com.l_george.hotels.data.repository

import com.l_george.hotels.apiService.api.ApiService
import com.l_george.hotels.domain.models.hotelModel.HotelModel
import java.lang.Exception

class HotelRepositoryImpl(private val apiService: ApiService) : HotelRepository {

    override suspend fun getHotel(): HotelModel {
        val  response = apiService.getHotel()
        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception()
        }
    }


}