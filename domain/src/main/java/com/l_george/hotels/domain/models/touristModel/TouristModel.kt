package com.l_george.hotels.domain.models.touristModel

data class TouristModel(
    val id: Int,
    val name: String,
    val secondName: String,
    val date: String,
    val country: String,
    val passportNum: Int,
    val passportDate: String,
    val typeView: TouristViewType,
    val isOpen:Boolean = false
)

sealed class TouristViewType() {
    data object TypeTourist : TouristViewType()

    data object TypeAddTourist : TouristViewType()
}
