package ru.dw.material.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

const val CONSTANT_FORMAT_DATE = "YYYY-MM-dd"
const val TAG_FRAGMENT_LAYOUT = "fragment-layout"
const val TAG_FRAGMENT_DAY = "fragment-day"
const val TAG_FRAGMENT_EARTH = "fragment-earth"
const val TAG_FRAGMENT_MARS = "fragment-mars"
const val TAG_FRAGMENT_CONSTRAINT = "fragment-constraint"
const val TAG_FRAGMENT_COORDINATOR = "fragment-coordinator"
const val TAG_FRAGMENT_MOTION = "fragment-motion"
const val TAG_FRAGMENT_CONSTRAIN_SET = "fragment-Constrain-set"
const val TAG_FRAGMENT_TRANSITION = "fragment-transition"
const val TAG_FRAGMENT_EXPLODE = "fragment-explode"
const val TAG_FRAGMENT_ZOOM = "fragment-zoom"
const val TAG_FRAGMENT_OBJECT_ANIM = "fragment-object-animation"
const val TAG_FRAGMENT_STATE_LIST = "fragment-object-state-list-animator"

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

@SuppressLint("SimpleDateFormat", "WeekBasedYear")
fun getDaysAgo(daysAgo: Int): String {
    val sdf = SimpleDateFormat("YYYY-MM-dd")
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
    return sdf.format(calendar.time)
}



