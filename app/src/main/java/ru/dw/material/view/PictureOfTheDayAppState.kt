package ru.dw.material.view

import ru.dw.material.model.ResponseDataItemDay


sealed class PictureOfTheDayAppState {
    data class Success(val responseDataItemDay: ResponseDataItemDay) : PictureOfTheDayAppState()
    data class Error(val error: String) : PictureOfTheDayAppState()
    object Loading : PictureOfTheDayAppState()
}
