package ru.dw.material.view.mars.viewmodel

import ru.dw.material.dto.PhotosItem


sealed class AppStateFragmentMars {
    data class Success(val responseDataListEarth: List<PhotosItem?>) : AppStateFragmentMars()
    data class Error(val error: String) : AppStateFragmentMars()
    object Loading : AppStateFragmentMars()
}
