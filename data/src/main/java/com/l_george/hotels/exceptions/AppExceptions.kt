package com.l_george.hotels.exceptions

sealed class AppExceptions(override var message:String) : RuntimeException() {

}

class NetworkError() : AppExceptions(NETWORK_ERROR_MESSAGE)
class UnknownError : AppExceptions(UNKNOWN_ERROR_MESSAGE)
class ApiError : AppExceptions(API_ERROR_MESSAGE)

private const val NETWORK_ERROR_MESSAGE = "Ошибка соединения. Проверьте подключение к сети."
private const val UNKNOWN_ERROR_MESSAGE = "Неизвестная ошибка"
private const val API_ERROR_MESSAGE = "Ошибка на сервере. Попробуйте позже."