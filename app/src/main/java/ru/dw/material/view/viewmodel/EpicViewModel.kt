package ru.dw.material.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.material.model.ResponseEarth
import ru.dw.material.repository.PictureOfTheDayRetrofitImpl

class EpicViewModel(
    private val liveData:MutableLiveData<AppStateFragmentEpic> = MutableLiveData(),
    private val api: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl
):ViewModel() {
    fun getLiveData(): MutableLiveData<AppStateFragmentEpic> = liveData
    fun sendRequest() {
        liveData.postValue(AppStateFragmentEpic.Loading)

        api.getEarth(object :CallbackResponseEpic{
            override fun onResponseSuccess(successes: List<ResponseEarth>) {
                liveData.postValue(AppStateFragmentEpic.Success(successes))
            }

            override fun onFail(error: String) {
                liveData.postValue(AppStateFragmentEpic.Error(error))
            }

        })
    }
}