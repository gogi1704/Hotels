package com.l_george.hotels.domain.models.touristModel

data class TouristModel(
    val id: Int,
    val name: String,
    val secondName: String,
    val date: String,
    val country: String,
    val passportNum: String,
    val passportDate: String,
    val typeView: TouristViewType,
    val isOpen:Boolean = false
)

sealed class TouristViewType() {
    data object TypeTourist : TouristViewType()

    data object TypeAddTourist : TouristViewType()
}


const val CONTENT_TYPE_NAME = "CONTENT_TYPE_NAME"
const val CONTENT_TYPE_SECOND_NAME = "CONTENT_TYPE_SECOND_NAME"
const val CONTENT_TYPE_DATE = "CONTENT_TYPE_DATE"
const val CONTENT_TYPE_COUNTRY = "CONTENT_TYPE_COUNTRY"
const val CONTENT_TYPE_PASSPORT_NUM = "CONTENT_TYPE_PASSPORT_NUM"
const val CONTENT_TYPE_PASSPORT_DATE = "CONTENT_TYPE_PASSPORT_DATE"
