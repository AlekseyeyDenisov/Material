package ru.dw.material.view.pictureoftheday.viewmodel

import ru.dw.material.dto.ResponseDataItemDay


sealed class AppStateFragmentDay {
    data class Success(val responseDataItemDay: ResponseDataItemDay) : AppStateFragmentDay()
    data class Error(val error: String) : AppStateFragmentDay()
    object Loading : AppStateFragmentDay()
}
