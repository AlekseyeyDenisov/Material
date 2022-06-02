package ru.dw.material.view.pictureoftheday.viewmodel

import ru.dw.material.dto.ResponseDataItemDay

interface CallbackResponseOfTheDay {
    fun onResponseSuccess(success: ResponseDataItemDay)
    fun onFail(error: String)
}