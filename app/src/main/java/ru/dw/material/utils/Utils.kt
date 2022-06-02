package ru.dw.material.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

const val CONSTANT_FORMAT_DATE = "YYYY-MM-dd"

@SuppressLint("SimpleDateFormat", "WeekBasedYear")
fun convertDateFormatApi(miles: Long): String {
    val outputFormat = SimpleDateFormat(CONSTANT_FORMAT_DATE)
    return outputFormat.format(miles)
}

@SuppressLint("SimpleDateFormat")
fun convertDateFormatUrlImages(miles: Long): String {
    val formatterDay = SimpleDateFormat("dd").format(miles)
    val formatterMonth = SimpleDateFormat("MM").format(miles)
    val formatterYear = SimpleDateFormat("yyyy").format(miles)
    return "$formatterYear/$formatterMonth/$formatterDay"
}

