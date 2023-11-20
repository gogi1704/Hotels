package com.l_george.hotels.viewModels.hotelViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l_george.domain2.repository.DomainRepository
import com.l_george.hotels.domain.models.hotelModel.HotelModel
import com.l_george.hotels.exceptions.ApiError
import com.l_george.hotels.exceptions.AppExceptions
import com.l_george.hotels.exceptions.NetworkError
import com.l_george.hotels.exceptions.UnknownError
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HotelViewModel(private val repository: DomainRepository) : ViewModel() {

    val progressState = MutableLiveData(false)
    private var error:AppExceptions? = null
        set(value) {
            field = value
            errorLiveData.value = value
        }
    val errorLiveData = MutableLiveData(error)
    private var hotel: HotelModel? = null
        set(value) {
            field = value
            hotelLiveData.value = value
        }
    val hotelLiveData = MutableLiveData(hotel)

    private var hotelImages = listOf<CarouselItem>()
        set(value) {
            field = value
            hotelImagesLiveData.value = value
        }
    val hotelImagesLiveData = MutableLiveData(hotelImages)

    private fun getHotel() {
        progressState.value = true
        viewModelScope.launch {
            try {
                val hotelFromApi = repository.getHotel()
                hotel = hotelFromApi
                hotelImages = hotelFromApi.image_urls.map { CarouselItem(imageUrl = it) }
                progressState.value = false
            } catch (api: ApiError) {
                error = ApiError()
            } catch (io: NetworkError) {
                error = NetworkError()
            } catch (unknown: UnknownError) {
                error = UnknownError()
            }

        }
    }

    init {
        getHotel()
    }

}