package ru.dw.material.repository

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dw.material.BuildConfig
import ru.dw.material.model.ResponseDataItemDay
import ru.dw.material.view.picture.PictureOfTheDayFragmentViewModel


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
            .create(PictureOfTheDayAPI::class.java)
            .getPictureOfTheDay(BuildConfig.NASA_API_KEY, whatDay, whatDay)
            .enqueue(
                object : Callback<List<ResponseDataItemDay>> {
                    override fun onResponse(
                        call: Call<List<ResponseDataItemDay>>,
                        responseList: Response<List<ResponseDataItemDay>>
                    ) {
                        responseList.body()
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


}