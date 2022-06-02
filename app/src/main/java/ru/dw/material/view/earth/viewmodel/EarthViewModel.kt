package ru.dw.material.view.earth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.material.dto.ResponseEarth
import ru.dw.material.repository.PictureOfTheDayRetrofitImpl

class EarthViewModel(
    private val liveData:MutableLiveData<AppStateFragmentEarth> = MutableLiveData(),
    private val api: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl
):ViewModel() {
    fun getLiveData(): MutableLiveData<AppStateFragmentEarth> = liveData
    fun sendRequest() {
        liveData.postValue(AppStateFragmentEarth.Loading)

        api.getEarth(object : CallbackResponseEarth {
            override fun onResponseSuccess(successes: List<ResponseEarth>) {
                liveData.postValue(AppStateFragmentEarth.Success(successes))
            }

            override fun onFail(error: String) {
                liveData.postValue(AppStateFragmentEarth.Error(error))
            }

        })
    }
}