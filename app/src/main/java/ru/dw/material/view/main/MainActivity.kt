package ru.dw.material.view.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ru.dw.material.R
import ru.dw.material.databinding.ActivityMainBinding
import ru.dw.material.utils.CONSTANT_THEMES_BLU
import ru.dw.material.utils.CONSTANT_THEMES_GREEN
import ru.dw.material.utils.CONSTANT_THEMES_RED
import ru.dw.material.utils.ConstantNasa.TAG
import ru.dw.material.utils.SharedPreferencesManagerNasa
import ru.dw.material.view.main.dialog.DialogChangeThemes
import ru.dw.material.view.pictureoftheday.PictureOfTheDayFragment


class MainActivity : AppCompatActivity() {
    private lateinit var pref: SharedPreferencesManagerNasa
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = SharedPreferencesManagerNasa(this)
        choiceTheme()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigation()
    }

    private fun bottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_of_the_day -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, PictureOfTheDayFragment.newInstance()).commit()
                    true
                }
                R.id.page_2 -> {
                    true
                }
                R.id.page_3 -> {
                    true
                }
                else -> {
                    true
                }
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.page_of_the_day
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dialog = DialogChangeThemes()
        when (item.itemId) {
            R.id.action_bar_theme -> {
                val currentTheme = pref.getThemesNightDay()
                if (currentTheme) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    pref.setThemesNightDay(!currentTheme)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    pref.setThemesNightDay(!currentTheme)
                }
            }
            R.id.action_navigation_theme_red -> {
                pref.setThemes(CONSTANT_THEMES_RED)
                dialog.show(supportFragmentManager, "")
            }
            R.id.action_navigation_theme_blue -> {
                pref.setThemes(CONSTANT_THEMES_BLU)
                dialog.show(supportFragmentManager, "")
            }
            R.id.action_navigation_theme_green -> {
                pref.setThemes(CONSTANT_THEMES_GREEN)
                dialog.show(supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun choiceTheme() {
        when (pref.getThemes()) {
            CONSTANT_THEMES_RED -> {
                setTheme(R.style.RedThemeMaterial)
            }
            CONSTANT_THEMES_BLU -> {
                setTheme(R.style.BlueThemeMaterial)
            }
            CONSTANT_THEMES_GREEN -> {
                setTheme(R.style.GreenThemeMaterial)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}