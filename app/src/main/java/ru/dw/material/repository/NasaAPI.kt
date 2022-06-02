package ru.dw.material.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.dw.material.model.ResponseDataItemDay
import ru.dw.material.model.ResponseEarth


interface NasaAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("start_date") startData: String,
        @Query("end_date") endData: String
    ): Call<List<ResponseDataItemDay>>


    @GET("EPIC/api/natural/date/{date}")
    fun getEarth(
        @Path("date") date: String?,
        @Query("api_key") apiKey: String
    ): Call<List<ResponseEarth>>

}