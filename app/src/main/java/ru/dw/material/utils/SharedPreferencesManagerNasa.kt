package ru.dw.material.utils

import android.content.Context
import android.content.SharedPreferences

private const val CONSTANT_SHARED_PREFERENCES = "weather_sharedPreferences"
private const val SHARED_PREFERENCES_THEMES = "token_fcm"
const val CONSTANT_THEMES_RED = 0
const val CONSTANT_THEMES_BLU = 1
const val CONSTANT_THEMES_GREEN = 2
private const val S_P_THEMES_NIGHT_DAY = "night_day"
const val CONSTANT_THEMES_DAY = true
const val CONSTANT_THEMES_NIGHT = false


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
        pref.edit().putBoolean(S_P_THEMES_NIGHT_DAY, themes).apply()
    }

    fun getThemesNightDay(): Boolean {
        return pref.getBoolean(S_P_THEMES_NIGHT_DAY,CONSTANT_THEMES_DAY)
    }

}