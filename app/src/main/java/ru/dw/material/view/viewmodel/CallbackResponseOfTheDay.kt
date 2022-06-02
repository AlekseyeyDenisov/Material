package ru.dw.material.view.viewmodel

import ru.dw.material.model.ResponseDataItemDay

interface CallbackResponseOfTheDay {
    fun onResponseSuccess(success: ResponseDataItemDay)
    fun onFail(error: String)
}