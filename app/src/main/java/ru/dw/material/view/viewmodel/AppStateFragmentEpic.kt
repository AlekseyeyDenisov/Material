package ru.dw.material.view.viewmodel

import ru.dw.material.model.ResponseEarth


sealed class AppStateFragmentEpic {
    data class Success(val responseDataListEarth: List<ResponseEarth>) : AppStateFragmentEpic()
    data class Error(val error: String) : AppStateFragmentEpic()
    object Loading : AppStateFragmentEpic()
}
