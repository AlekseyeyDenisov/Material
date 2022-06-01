package ru.dw.material.repository

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dw.material.BuildConfig
import ru.dw.material.model.ResponseDataItemDay
import ru.dw.material.model.ResponseEpic
import ru.dw.material.utils.ConstantNasa.TAG
import ru.dw.material.view.pictureoftheday.PictureOfTheDayFragmentViewModel


object PictureOfTheDayRetrofitImpl {
    private val retrofit: Retrofit = initRetrofit()
    private const val NASA_BASE_URL = "https://api.nasa.gov/"

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(NASA_BASE_URL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build()
    }

    fun getListDayPicture(
        whatDay: String,
        callbackDetails: PictureOfTheDayFragmentViewModel.CallbackDetails
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
                                callbackDetails.onResponseSuccess(it[0])
                            }
                        } else {
                            callbackDetails.onFail("Error code: ${responseList.code()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<List<ResponseDataItemDay>>,
                        t: Throwable
                    ) {
                        t.message?.let {error->
                            callbackDetails.onFail(error)
                        }

                    }

                })

    }

    fun getEpic(){
        retrofit
            .create(NasaAPI::class.java)
            .getEpic(BuildConfig.NASA_API_KEY)
            .enqueue(
                object : Callback<List<ResponseEpic>> {
                    override fun onResponse(
                        call: Call<List<ResponseEpic>>,
                        responseList: Response<List<ResponseEpic>>
                    ) {
                        Log.d(TAG, "onResponse: ${ call.request()}")
                        if (responseList.isSuccessful) {
                            Log.d(TAG, "onResponse: ${responseList.body()}")
                            responseList.body()
                               // callbackDetails.onResponseSuccess(it[0])
                            
                        } else {
                           // callbackDetails.onFail("Error code: ${responseList.code()}")
                        }
                    }

                    override fun onFailure(
                        call: Call<List<ResponseEpic>>,
                        t: Throwable
                    ) {
                        t.message?.let {error->
                           // callbackDetails.onFail(error)
                        }

                    }

                })
    }


}