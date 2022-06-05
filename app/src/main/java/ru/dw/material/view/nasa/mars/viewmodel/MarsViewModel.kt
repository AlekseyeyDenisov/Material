package ru.dw.material.view.nasa.mars.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.material.dto.PhotosItem
import ru.dw.material.repository.PictureOfTheDayRetrofitImpl

class MarsViewModel(
    private val liveData: MutableLiveData<AppStateFragmentMars> = MutableLiveData(),
    private val api: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl
) : ViewModel() {
    fun getLiveData(): MutableLiveData<AppStateFragmentMars> = liveData
    fun sendRequest() {
        liveData.postValue(AppStateFragmentMars.Loading)
        api.getMars(object : CallbackResponseMars {
            override fun onResponseSuccess(successes: List<PhotosItem?>) {
                liveData.postValue(AppStateFragmentMars.Success(successes))
            }

            override fun onFail(error: String) {
                liveData.postValue(AppStateFragmentMars.Error(error))
            }

        })
    }
}