package ru.dw.material.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.dw.material.R
import ru.dw.material.utils.CONSTANT_THEMES_BLU
import ru.dw.material.utils.CONSTANT_THEMES_GREEN
import ru.dw.material.utils.CONSTANT_THEMES_RED
import ru.dw.material.utils.SharedPreferencesManagerNasa
import ru.dw.material.view.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = SharedPreferencesManagerNasa(this)
        when (pref.getThemes()) {
            CONSTANT_THEMES_RED -> {setTheme(R.style.RedThemeMaterial)}
            CONSTANT_THEMES_BLU -> {setTheme(R.style.BlueThemeMaterial)}
            CONSTANT_THEMES_GREEN -> {setTheme(R.style.GreenThemeMaterial)}
        }
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance()).commit()
        }
    }
}