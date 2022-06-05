package ru.dw.material.view.nasa.pictureoftheday.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.material.dto.ResponseDataItemDay
import ru.dw.material.repository.PictureOfTheDayRetrofitImpl


class PictureOfTheDayFragmentViewModel(
    private val liveData: MutableLiveData<AppStateFragmentDay> = MutableLiveData(),
    private val api: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl
) : ViewModel() {

    fun getLiveData(): MutableLiveData<AppStateFragmentDay> = liveData


    fun sendRequest(day: String) {
        liveData.postValue(AppStateFragmentDay.Loading)

        api.getListDayPicture(day, object : CallbackResponseOfTheDay {
            override fun onResponseSuccess(success: ResponseDataItemDay) {
                liveData.postValue(AppStateFragmentDay.Success(success))
            }

            override fun onFail(error: String) {
                liveData.postValue(AppStateFragmentDay.Error(error))
            }
        })
    }





}