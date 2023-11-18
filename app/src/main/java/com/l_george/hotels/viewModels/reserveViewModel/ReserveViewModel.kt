package com.l_george.hotels.viewModels.reserveViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l_george.hotels.data.repository.HotelRepository
import com.l_george.hotels.domain.models.reserveModel.ReserveModel
import kotlinx.coroutines.launch


class ReserveViewModel(private val repository: HotelRepository) : ViewModel() {
    private var reserveModel: ReserveModel? = null
        set(value) {

            field = value
            reserveModelLiveData.value = value
        }
    val reserveModelLiveData = MutableLiveData(reserveModel)

    private fun getReserveHotel() {
        viewModelScope.launch {
            reserveModel = repository.getHotelReserved()
        }
    }

    init {
        getReserveHotel()
    }
}