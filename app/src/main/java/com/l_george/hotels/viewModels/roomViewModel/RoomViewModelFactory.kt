package com.l_george.hotels.viewModels.roomViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.l_george.hotels.data.repository.HotelRepository


class RoomViewModelFactory(private val repository: HotelRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return RoomViewModel(repository) as T
    }
}