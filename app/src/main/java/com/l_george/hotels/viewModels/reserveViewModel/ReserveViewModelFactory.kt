package com.l_george.hotels.viewModels.reserveViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.l_george.hotels.data.repository.HotelRepository
import com.l_george.hotels.viewModels.roomViewModel.RoomViewModel

class ReserveViewModelFactory(private val repository: HotelRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return ReserveViewModel(repository) as T
    }
}