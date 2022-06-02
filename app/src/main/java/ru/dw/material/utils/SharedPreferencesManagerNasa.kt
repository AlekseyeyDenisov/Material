package ru.dw.material.utils

import android.content.Context
import android.content.SharedPreferences

private const val CONSTANT_SHARED_PREFERENCES = "weather_sharedPreferences"
private const val SHARED_PREFERENCES_THEMES = "token_fcm"
private const val SHARED_PREFERENCES__THEMES_NIGHT_DAY = "night_day"
private const val SHARED_PREFERENCES_DATE_API = "SHARED_PREFERENCES_DATE_API"
private const val SHARED_PREFERENCES_DATE_URL = "SHARED_PREFERENCES_DATE_URL"
private const val CONSTANT_DAY_DEFAULT_API = "2022-05-01"
private const val CONSTANT_DAY_DEFAULT_URL = "2022/05/01"
const val CONSTANT_THEMES_RED = 0
const val CONSTANT_THEMES_BLU = 1
const val CONSTANT_THEMES_GREEN = 2
const val CONSTANT_THEMES_DAY = true


class SharedPreferencesManagerNasa(context: Context) {
    private var pref: SharedPreferences =
        context.getSharedPreferences(CONSTANT_SHARED_PREFERENCES, Context.MODE_PRIVATE)


    fun setThemes(themes: Int) {
        pref.edit().putInt(SHARED_PREFERENCES_THEMES, themes).apply()
    }

    fun getThemes(): Int {
        return pref.getInt(SHARED_PREFERENCES_THEMES,CONSTANT_THEMES_RED)
    }

    fun setThemesNightDay(themes: Boolean) {
        pref.edit().putBoolean(SHARED_PREFERENCES__THEMES_NIGHT_DAY, themes).apply()
    }

    fun getThemesNightDay(): Boolean {
        return pref.getBoolean(SHARED_PREFERENCES__THEMES_NIGHT_DAY,CONSTANT_THEMES_DAY)
    }

    fun setDateDayApi(date: String) {
        pref.edit().putString(SHARED_PREFERENCES_DATE_API, date).apply()
    }

    fun getTDateDayAPi(): String? {
        return pref.getString(SHARED_PREFERENCES_DATE_API,CONSTANT_DAY_DEFAULT_API)
    }
    fun setDateDayUrl(date: String) {
        pref.edit().putString(SHARED_PREFERENCES_DATE_URL, date).apply()
    }

    fun getTDateDayUrl(): String? {
        return pref.getString(SHARED_PREFERENCES_DATE_URL, CONSTANT_DAY_DEFAULT_URL)
    }

}