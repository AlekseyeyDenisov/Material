package ru.dw.material.view.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.dw.material.R
import ru.dw.material.databinding.ActivityMainBinding
import ru.dw.material.utils.*
import ru.dw.material.view.main.dialog.DialogChangeThemes


class MainActivity : AppCompatActivity() {
    private lateinit var pref: SharedPreferencesManagerNasa
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = SharedPreferencesManagerNasa(this)
        choiceTheme()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController


        ///bottomNavigation()

//        if (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_LAYOUT) == null) {
//            goToFragment(LayoutFragment.newInstance(),TAG_FRAGMENT_LAYOUT)
//        }
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
                    navController.navigate(R.id.action_nasaApiFragment_to_layoutFragment)
            }
            R.id.action_home -> {
                navController.navigate(R.id.action_global_nasaApiFragment)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }
}