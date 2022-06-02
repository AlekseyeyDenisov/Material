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
import ru.dw.material.dto.ResponseDataItemDay
import ru.dw.material.dto.ResponseEarth
import ru.dw.material.dto.ResponseMars
import ru.dw.material.utils.ConstantNasa.TAG
import ru.dw.material.view.earth.viewmodel.CallbackResponseEarth
import ru.dw.material.view.mars.viewmodel.CallbackResponseMars
import ru.dw.material.view.pictureoftheday.viewmodel.CallbackResponseOfTheDay


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

    fun getEarth(callbackResponseEarth: CallbackResponseEarth) {
        Log.d(TAG, "getEpic: ${MyApp.pref.getTDateDayAPi()}")
        retrofit
            .create(NasaAPI::class.java)
            .getEarth(MyApp.pref.getTDateDayAPi(), BuildConfig.NASA_API_KEY)
            .enqueue(
                object : Callback<List<ResponseEarth>> {
                    override fun onResponse(
                        call: Call<List<ResponseEarth>>,
                        responseList: Response<List<ResponseEarth>>
                    ) {
                        if (responseList.isSuccessful) {
                            responseList.body()?.let {
                                callbackResponseEarth.onResponseSuccess(it)
                            }
                        } else {
                            callbackResponseEarth.onFail("Error code: ${responseList.code()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<List<ResponseEarth>>,
                        t: Throwable
                    ) {
                        t.message?.let { error ->
                            Log.d(TAG, "onFailure: $error")
                            callbackResponseEarth.onFail(error)
                        }

                    }

                })
    }

    fun getMars(callbackResponseMars: CallbackResponseMars) {
        retrofit
            .create(NasaAPI::class.java)
            .getMarsImageByDate(MyApp.pref.getTDateDayAPi(), BuildConfig.NASA_API_KEY)
            .enqueue(
                object : Callback<ResponseMars> {
                    override fun onResponse(
                        call: Call<ResponseMars>,
                        responseList: Response<ResponseMars>
                    ) {
                        if (responseList.isSuccessful) {
                            responseList.body()?.let { response ->
                                Log.d(TAG, "onResponse: ${response.photos?.size}")
                                response.photos?.let { listPhotos ->
                                    callbackResponseMars.onResponseSuccess(listPhotos)
                                }

                            }
                        } else {
                            callbackResponseMars.onFail("Error code: ${responseList.code()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<ResponseMars>,
                        t: Throwable
                    ) {
                        t.message?.let { error ->
                            Log.d(TAG, "onFailure: $error")
                            callbackResponseMars.onFail(error)
                        }

                    }

                })
    }


}