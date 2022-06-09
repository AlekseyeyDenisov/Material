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
        launchFragment(PictureOfTheDayFragment.newInstance(), TAG_FRAGMENT_DAY)
        bottomNavigation()
    }


    private fun bottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_of_the_day -> {
                    launchFragment(PictureOfTheDayFragment.newInstance(), TAG_FRAGMENT_DAY)
                    true
                }
                R.id.page_earth -> {
                    launchFragment(EarthFragment.newInstance(), TAG_FRAGMENT_EARTH)
                    true
                }
                R.id.page_mars -> {
                    launchFragment(MarsFragment.newInstance(), TAG_FRAGMENT_MARS)
                    true
                }
                else -> {
                    true
                }
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.page_of_the_day
    }

    private fun launchFragment(fragment: Fragment, tagFragment: String) {
        if (requireActivity().supportFragmentManager.findFragmentByTag(tagFragment) == null
        ) {
            requireActivity().supportFragmentManager.apply {
                beginTransaction()
                    .setCustomAnimations(
                        R.anim.to_left_in,
                        R.anim.to_left_out,
                        R.anim.to_right_in,
                        R.anim.to_right_out
                    )
                    .addToBackStack(tagFragment)
                    .add(R.id.container_nasa_api, fragment, tagFragment)
                    .commit()
            }
        } else {
            requireActivity().supportFragmentManager
                .popBackStack(tagFragment, 0)
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