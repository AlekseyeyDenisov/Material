package ru.dw.material.view.earth.viewmodel


import ru.dw.material.dto.ResponseEarth

interface CallbackResponseEarth {
    fun onResponseSuccess(successes: List<ResponseEarth>)
    fun onFail(error: String)
}