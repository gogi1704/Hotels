package com.l_george.hotels.data.repository

import com.l_george.hotels.apiService.api.ApiService
import com.l_george.hotels.domain.models.hotelModel.HotelModel
import com.l_george.hotels.domain.models.reserveModel.ReserveModel
import com.l_george.hotels.domain.models.roomModel.RoomModel
import com.l_george.hotels.exceptions.ApiError
import com.l_george.hotels.exceptions.NetworkError
import java.io.IOException
import kotlin.Exception

class HotelRepositoryImpl(private val apiService: ApiService) : HotelRepository {


    override suspend fun getHotel(): HotelModel {
        try {
            val response = apiService.getHotel()
            if (response.isSuccessful) {
                return response.body() ?: throw ApiError()
            } else throw ApiError()
        } catch (io: IOException) {
            throw NetworkError()
        } catch (e: Exception) {
            throw UnknownError()
        }

    }

    override suspend fun getHotelReserved(): ReserveModel {
        try {
            val response = apiService.getHotelToReserve()
            if (response.isSuccessful) {
                return response.body() ?: throw ApiError()
            } else {
                throw ApiError()
            }
        } catch (io: IOException) {
            throw NetworkError()
        } catch (e: Exception) {
            throw UnknownError()
        }


    }

    override suspend fun getRooms(): List<RoomModel> {
        try {
            val response = apiService.getRooms()
            if (response.isSuccessful) {
                return response.body()?.rooms ?: throw ApiError()
            } else {
                throw ApiError()
            }
        } catch (io: IOException) {
            throw NetworkError()
        } catch (e: Exception) {
            throw com.l_george.hotels.exceptions.UnknownError()
        }


    }


}