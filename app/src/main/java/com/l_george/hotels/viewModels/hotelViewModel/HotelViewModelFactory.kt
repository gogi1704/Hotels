package com.l_george.hotels.viewModels.hotelViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.l_george.domain2.repository.DomainRepository

class HotelViewModelFactory(private val repository: DomainRepository) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return HotelViewModel(repository) as T
    }
}