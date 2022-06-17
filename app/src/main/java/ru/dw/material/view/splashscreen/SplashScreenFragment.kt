package ru.dw.material.view.splashscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import ru.dw.material.R
import ru.dw.material.databinding.FragmentNasaApiBinding
import ru.dw.material.databinding.FragmentSplashScreenBinding
import ru.dw.material.utils.TAG_FRAGMENT_DAY
import ru.dw.material.view.nasa.pictureoftheday.PictureOfTheDayFragment


class SplashScreenFragment : Fragment() {
    lateinit var navController: NavController

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding: FragmentSplashScreenBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        lifecycleScope.launch(Dispatchers.Main){
            animation()
        }
    }

    private suspend fun animation() {

        binding.SplashBackground.animate().rotation(720F).setDuration(3000).start()
        delay(2000)
        navController.navigate(R.id.nasaApiFragment)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}