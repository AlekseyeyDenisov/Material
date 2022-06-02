package ru.dw.material.view.mars.viewmodel


import ru.dw.material.dto.PhotosItem


interface CallbackResponseMars {
    fun onResponseSuccess(successes: List<PhotosItem?>)
    fun onFail(error: String)
}