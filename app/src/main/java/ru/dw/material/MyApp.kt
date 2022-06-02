package ru.dw.material

import android.app.Application
import ru.dw.material.utils.SharedPreferencesManagerNasa


class MyApp : Application() {

    companion object {
        private var appContext: MyApp? = null
        lateinit var pref: SharedPreferencesManagerNasa
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        pref = SharedPreferencesManagerNasa(this)


    }


}