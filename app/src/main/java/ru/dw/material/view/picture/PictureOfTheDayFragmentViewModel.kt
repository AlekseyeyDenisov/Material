package ru.dw.material.view.picture


import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dw.material.model.ResponseDataItemDay
import ru.dw.material.repository.PictureOfTheDayRetrofitImpl
import java.text.SimpleDateFormat
import java.util.*


class PictureOfTheDayFragmentViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayAppState> = MutableLiveData(),
    private val api: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl
) : ViewModel() {

    fun getLiveData(): MutableLiveData<PictureOfTheDayAppState> {
        return liveData
    }

    fun sendRequest(daysAgo: Int) {
        liveData.postValue(PictureOfTheDayAppState.Loading)

        api.getListDayPicture(getDaysAgo(daysAgo), object : CallbackDetails {
            override fun onResponseSuccess(success: ResponseDataItemDay) {
                liveData.postValue(PictureOfTheDayAppState.Success(success))
            }

            override fun onFail(error: String) {
                liveData.postValue(PictureOfTheDayAppState.Error(error))
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

    interface CallbackDetails {
        fun onResponseSuccess(success: ResponseDataItemDay)
        fun onFail(error: String)
    }

}