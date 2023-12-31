package com.l_george.hotels.viewModels.roomViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l_george.domain2.repository.DomainRepository
import com.l_george.hotels.domain.models.roomModel.RoomModel
import com.l_george.hotels.exceptions.ApiError
import com.l_george.hotels.exceptions.AppExceptions
import com.l_george.hotels.exceptions.NetworkError
import com.l_george.hotels.exceptions.UnknownError
import kotlinx.coroutines.launch

class RoomViewModel(private val repository: DomainRepository) : ViewModel() {

    val progressState = MutableLiveData(false)

    private var error: AppExceptions? = null
        set(value) {
            field = value
            errorLiveData.value = value
        }
    val errorLiveData = MutableLiveData(error)

    private var listRooms = emptyList<RoomModel>()
        set(value) {
            field = value
            listRoomsLiveData.value = value
        }

    val listRoomsLiveData = MutableLiveData(listRooms)

    private fun getRooms(){
        viewModelScope.launch {
            progressState.value = true
            try {
                listRooms = repository.getRooms()
                progressState.value = false
            }catch (api: ApiError) {
                error = ApiError()
            } catch (io: NetworkError) {
                error = NetworkError()
            } catch (unknown: UnknownError) {
                error = UnknownError()
            }
        }
    }

    init {
        getRooms()
    }

}