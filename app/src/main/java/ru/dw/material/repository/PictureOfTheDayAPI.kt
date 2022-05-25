package ru.dw.material.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.dw.material.model.ResponseDataItemDay


interface PictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("start_date") startData: String,
        @Query("end_date") endData: String
    ): Call<List<ResponseDataItemDay>>


}