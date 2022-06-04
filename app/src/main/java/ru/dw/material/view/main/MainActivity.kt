package ru.dw.material.view.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import ru.dw.material.R
import ru.dw.material.databinding.ActivityMainBinding
import ru.dw.material.utils.*
import ru.dw.material.view.earth.EarthFragment
import ru.dw.material.view.layout.LayoutFragment
import ru.dw.material.view.main.dialog.DialogChangeThemes
import ru.dw.material.view.mars.MarsFragment
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

    fun hideBottomBar(isHidden: Boolean) {
        binding.bottomNavigation.setVisibility(if (isHidden) View.GONE else View.VISIBLE)
    }

    private fun bottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_of_the_day -> {
                    if (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_DAY) == null) {
                        goToFragment(PictureOfTheDayFragment.newInstance(),TAG_FRAGMENT_DAY)
                    }
                    true
                }
                R.id.page_earth -> {
                    if (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_EARTH) == null) {
                        goToFragment(EarthFragment.newInstance(),TAG_FRAGMENT_EARTH)
                    }
                    true
                }
                R.id.page_mars -> {
                    if (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_MARS) == null) {
                        goToFragment(MarsFragment.newInstance(),TAG_FRAGMENT_MARS)
                    }
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
            R.id.action_layout -> {
                if (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_LAYOUT) == null) {
                    goToFragment(LayoutFragment.newInstance(),TAG_FRAGMENT_LAYOUT)
                }
            }
            R.id.action_home -> {

                if (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_DAY) == null) {
                    goToFragment(PictureOfTheDayFragment.newInstance(),TAG_FRAGMENT_DAY)
                    binding.bottomNavigation.selectedItemId = R.id.page_of_the_day
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun goToFragment(fragment: Fragment, tagFragment: String) {
        supportFragmentManager.apply {
            beginTransaction()
                .replace(R.id.container, fragment, tagFragment)
                .commit()
        }
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