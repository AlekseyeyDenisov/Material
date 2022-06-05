package ru.dw.material.view.nasa.earth.viewmodel

import ru.dw.material.dto.ResponseEarth


sealed class AppStateFragmentEarth {
    data class Success(val responseDataListEarth: List<ResponseEarth>) : AppStateFragmentEarth()
    data class Error(val error: String) : AppStateFragmentEarth()
    object Loading : AppStateFragmentEarth()
}
