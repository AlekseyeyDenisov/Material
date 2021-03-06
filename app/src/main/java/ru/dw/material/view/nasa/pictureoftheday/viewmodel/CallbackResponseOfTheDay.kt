package ru.dw.material.view.nasa.pictureoftheday.viewmodel

import ru.dw.material.dto.ResponseDataItemDay

interface CallbackResponseOfTheDay {
    fun onResponseSuccess(success: ResponseDataItemDay)
    fun onFail(error: String)
}