package ru.dw.material.repository

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dw.material.BuildConfig
import ru.dw.material.MyApp
import ru.dw.material.model.ResponseDataItemDay
import ru.dw.material.model.ResponseEarth
import ru.dw.material.utils.ConstantNasa.TAG
import ru.dw.material.view.viewmodel.CallbackResponseEpic
import ru.dw.material.view.viewmodel.CallbackResponseOfTheDay


object PictureOfTheDayRetrofitImpl {
    private val retrofit: Retrofit = initRetrofit()
    const val NASA_BASE_URL = "https://api.nasa.gov/"

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(NASA_BASE_URL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build()
    }

    fun getListDayPicture(
        whatDay: String,
        callbackResponseOfTheDay: CallbackResponseOfTheDay
    ) {
        retrofit
            .create(NasaAPI::class.java)
            .getPictureOfTheDay(BuildConfig.NASA_API_KEY, whatDay, whatDay)
            .enqueue(
                object : Callback<List<ResponseDataItemDay>> {
                    override fun onResponse(
                        call: Call<List<ResponseDataItemDay>>,
                        responseList: Response<List<ResponseDataItemDay>>
                    ) {

                        if (responseList.isSuccessful) {
                            responseList.body()?.let {
                                callbackResponseOfTheDay.onResponseSuccess(it[0])
                            }
                        } else {
                            callbackResponseOfTheDay.onFail("Error code: ${responseList.code()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<List<ResponseDataItemDay>>,
                        t: Throwable
                    ) {
                        t.message?.let { error ->
                            callbackResponseOfTheDay.onFail(error)
                        }

                    }

                })

    }

    fun getEarth(callbackResponseEpic: CallbackResponseEpic) {
        Log.d(TAG, "getEpic: ${MyApp.pref.getTDateDayAPi()}")
        retrofit
            .create(NasaAPI::class.java)
            .getEarth(MyApp.pref.getTDateDayAPi(),BuildConfig.NASA_API_KEY)
            .enqueue(
                object : Callback<List<ResponseEarth>> {
                    override fun onResponse(
                        call: Call<List<ResponseEarth>>,
                        responseList: Response<List<ResponseEarth>>
                    ) {
                        if (responseList.isSuccessful) {
                            responseList.body()?.let {
                                callbackResponseEpic.onResponseSuccess(it)
                            }
                        } else {
                            callbackResponseEpic.onFail("Error code: ${responseList.code()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<List<ResponseEarth>>,
                        t: Throwable
                    ) {
                        t.message?.let { error ->
                            Log.d(TAG, "onFailure: $error")
                            callbackResponseEpic.onFail(error)
                        }

                    }

                })
    }


}