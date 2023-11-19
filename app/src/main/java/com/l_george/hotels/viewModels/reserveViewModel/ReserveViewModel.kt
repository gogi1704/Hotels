package com.l_george.hotels.viewModels.reserveViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l_george.hotels.data.repository.HotelRepository
import com.l_george.hotels.domain.models.reserveModel.ReserveModel
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_COUNTRY
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_DATE
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_NAME
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_PASSPORT_DATE
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_PASSPORT_NUM
import com.l_george.hotels.domain.models.touristModel.CONTENT_TYPE_SECOND_NAME
import com.l_george.hotels.domain.models.touristModel.TouristModel
import com.l_george.hotels.domain.models.touristModel.TouristViewType
import kotlinx.coroutines.launch


class ReserveViewModel(private val repository: HotelRepository) : ViewModel() {

    private var reserveModel: ReserveModel? = null
        set(value) {

            field = value
            reserveModelLiveData.value = value
        }
    val reserveModelLiveData = MutableLiveData(reserveModel)


    private var listTourist = mutableListOf<TouristModel>()
        set(value) {
            field = value
            listTouristLiveData.value = value
        }
    val listTouristLiveData = MutableLiveData(listTourist)

    val reservedCompleted = MutableLiveData(false)

    private val newModelTourist = TouristModel(
        id = 1000,
        name = "",
        secondName = "",
        date = "",
        country = "",
        passportNum = "",
        passportDate = "",
        typeView = TouristViewType.TypeTourist
    )


    fun addNewTourist() {
        viewModelScope.launch {
            val newList = listTourist
            newList.add(newModelTourist.copy(id = listTourist.size - 1))
            listTourist = newList.sortedWith(compareBy { it.id }).toMutableList()
        }

    }

    fun openTouristItem(itemId: Int, isOpen: Boolean) {
        viewModelScope.launch {
            listTourist =
                listTourist.map { if (it.id == itemId) it.copy(isOpen = !isOpen) else it }
                    .toMutableList()
        }

    }

    fun checkAll() {
        viewModelScope.launch {
            listTourist = listTourist.map { it.copy(isChecked = true) }.toMutableList()
            var isOk = true
            for (item in listTourist) {
                if (item.typeView == TouristViewType.TypeTourist) {
                    if (item.name.isEmpty() ||
                        item.secondName.isEmpty() ||
                        item.date.isEmpty() ||
                        item.country.isEmpty() ||
                        item.passportNum.isEmpty() ||
                        item.passportDate.isEmpty()
                    ) {
                        isOk = false
                        break
                    }
                }
            }
            reservedCompleted.value = isOk

        }
    }


    fun saveDate(itemId: Int, contentType: String, content: String) {
        viewModelScope.launch {
            var model = listTourist.filter { it.id == itemId }[0]
            when (contentType) {
                CONTENT_TYPE_NAME -> {
                    model = model.copy(name = content)
                }

                CONTENT_TYPE_SECOND_NAME -> {
                    model = model.copy(secondName = content)
                }

                CONTENT_TYPE_DATE -> {
                    model = model.copy(date = content)
                }

                CONTENT_TYPE_COUNTRY -> {
                    model = model.copy(country = content)
                }

                CONTENT_TYPE_PASSPORT_DATE -> {
                    model = model.copy(passportDate = content)
                }

                CONTENT_TYPE_PASSPORT_NUM -> {
                    model = model.copy(passportNum = content)
                }
            }

            listTourist = listTourist.map { if (it.id == itemId) model else it }.toMutableList()
        }
    }


    private fun getReserveHotel() {
        viewModelScope.launch {
            reserveModel = repository.getHotelReserved()
        }
    }

    fun reset() {
        listTourist.clear()
        listTourist.add(newModelTourist.copy(id = 0))
        listTourist.add(newModelTourist.copy(typeView = TouristViewType.TypeAddTourist))
        reservedCompleted.value = false
    }

    init {
        listTourist.add(newModelTourist.copy(id = 0))
        listTourist.add(newModelTourist.copy(typeView = TouristViewType.TypeAddTourist))
        getReserveHotel()
    }
}