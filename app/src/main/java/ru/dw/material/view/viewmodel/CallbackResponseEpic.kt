package ru.dw.material.view.viewmodel


import ru.dw.material.model.ResponseEarth

interface CallbackResponseEpic {
    fun onResponseSuccess(successes: List<ResponseEarth>)
    fun onFail(error: String)
}