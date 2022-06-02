package ru.dw.material.view.viewmodel

import ru.dw.material.model.ResponseDataItemDay


sealed class AppStateFragmentDay {
    data class Success(val responseDataItemDay: ResponseDataItemDay) : AppStateFragmentDay()
    data class Error(val error: String) : AppStateFragmentDay()
    object Loading : AppStateFragmentDay()
}
