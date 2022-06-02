package ru.dw.material.view.pictureoftheday.viewmodel


import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.material.dto.ResponseDataItemDay
import ru.dw.material.repository.PictureOfTheDayRetrofitImpl
import java.text.SimpleDateFormat
import java.util.*


class PictureOfTheDayFragmentViewModel(
    private val liveData: MutableLiveData<AppStateFragmentDay> = MutableLiveData(),
    private val api: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl
) : ViewModel() {

    fun getLiveData(): MutableLiveData<AppStateFragmentDay> = liveData


    fun sendRequest(daysAgo: Int) {
        liveData.postValue(AppStateFragmentDay.Loading)

        api.getListDayPicture(getDaysAgo(daysAgo), object : CallbackResponseOfTheDay {
            override fun onResponseSuccess(success: ResponseDataItemDay) {
                liveData.postValue(AppStateFragmentDay.Success(success))
            }

            override fun onFail(error: String) {
                liveData.postValue(AppStateFragmentDay.Error(error))
            }
        })
    }

    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    private fun getDaysAgo(daysAgo: Int): String {
        val sdf = SimpleDateFormat("YYYY-MM-dd")
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return sdf.format(calendar.time)
    }



}