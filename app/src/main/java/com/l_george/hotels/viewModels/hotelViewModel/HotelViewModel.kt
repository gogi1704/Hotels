package com.l_george.hotels.viewModels.hotelViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l_george.hotels.data.repository.HotelRepository
import com.l_george.hotels.domain.models.hotelModel.HotelModel
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HotelViewModel(private val repository: HotelRepository) : ViewModel() {

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

    fun getHotel() {
        viewModelScope.launch {
            val hotelFromApi = repository.getHotel()
            hotel = hotelFromApi
            hotelImages = hotelFromApi.image_urls.map { CarouselItem(imageUrl = it) }
        }
    }

}