package ru.dw.material.view.nasa.earth.viewmodel


import ru.dw.material.dto.ResponseEarth

interface CallbackResponseEarth {
    fun onResponseSuccess(successes: List<ResponseEarth>)
    fun onFail(error: String)
}