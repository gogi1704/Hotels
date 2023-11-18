package com.l_george.hotels.viewModels.roomViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l_george.hotels.data.repository.HotelRepository
import com.l_george.hotels.domain.models.roomModel.RoomModel
import kotlinx.coroutines.launch

class RoomViewModel(private val repository: HotelRepository) : ViewModel() {

    private var listRooms = emptyList<RoomModel>()
        set(value) {
            field = value
            listRoomsLiveData.value = value
        }

    val listRoomsLiveData = MutableLiveData(listRooms)

    private fun getRooms(){
        viewModelScope.launch {
            listRooms = repository.getRooms()
        }
    }

    init {
        getRooms()
    }

}