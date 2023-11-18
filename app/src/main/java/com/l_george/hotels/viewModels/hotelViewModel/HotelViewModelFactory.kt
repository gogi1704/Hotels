package com.l_george.hotels.viewModels.hotelViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.l_george.hotels.data.repository.HotelRepository

class HotelViewModelFactory(private val repository: HotelRepository) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return HotelViewModel(repository) as T
    }
}