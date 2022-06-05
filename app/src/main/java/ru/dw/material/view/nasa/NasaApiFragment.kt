package ru.dw.material.view.layout.constraint


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dw.material.R
import ru.dw.material.databinding.FragmentNasaApiBinding
import ru.dw.material.utils.TAG_FRAGMENT_DAY
import ru.dw.material.utils.TAG_FRAGMENT_EARTH
import ru.dw.material.utils.TAG_FRAGMENT_MARS
import ru.dw.material.view.nasa.earth.EarthFragment
import ru.dw.material.view.nasa.mars.MarsFragment
import ru.dw.material.view.nasa.pictureoftheday.PictureOfTheDayFragment


class NasaApiFragment : Fragment() {


    private var _binding: FragmentNasaApiBinding? = null
    private val binding: FragmentNasaApiBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNasaApiBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToFragment(PictureOfTheDayFragment.newInstance(), TAG_FRAGMENT_DAY)
        bottomNavigation()
    }


    private fun bottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_of_the_day -> {
                    if (requireActivity().supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_DAY) == null) {
                        goToFragment(PictureOfTheDayFragment.newInstance(), TAG_FRAGMENT_DAY)
                    }
                    true
                }
                R.id.page_earth -> {
                    if (requireActivity().supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_EARTH) == null) {
                        goToFragment(EarthFragment.newInstance(), TAG_FRAGMENT_EARTH)
                    }
                    true
                }
                R.id.page_mars -> {
                    if (requireActivity().supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_MARS) == null) {
                        goToFragment(MarsFragment.newInstance(), TAG_FRAGMENT_MARS)
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

    private fun goToFragment(fragment: Fragment, tagFragment: String) {
        requireActivity().supportFragmentManager.apply {
            beginTransaction()
                .replace(R.id.container_nasa_api, fragment, tagFragment)
                .commit()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = NasaApiFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}