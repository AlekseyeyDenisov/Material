package ru.dw.material.view.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dw.material.R
import ru.dw.material.databinding.FargmentLayoutBinding
import ru.dw.material.utils.TAG_FRAGMENT_CONSTRAINT
import ru.dw.material.utils.TAG_FRAGMENT_COORDINATOR
import ru.dw.material.view.layout.constraint.ConstraintFragment
import ru.dw.material.view.layout.coordinator.CoordinatorFragment
import ru.dw.material.view.main.MainActivity


class LayoutFragment : Fragment() {
    private var _binding: FargmentLayoutBinding? = null
    private val binding: FargmentLayoutBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FargmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigation()

    }

    private fun bottomNavigation() {

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_constrain -> {
                    goToFragment(ConstraintFragment.newInstance(), TAG_FRAGMENT_CONSTRAINT)
                    true
                }
                R.id.action_coordinator -> {
                    if (requireActivity().supportFragmentManager.findFragmentByTag(
                            TAG_FRAGMENT_COORDINATOR
                        ) == null
                    ) {
                        goToFragment(CoordinatorFragment.newInstance(), TAG_FRAGMENT_COORDINATOR)
                    }
                    true
                }
                R.id.action_motion -> {

                    true
                }
                else -> {
                    true
                }
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.action_constrain
    }

    private fun goToFragment(fragment: Fragment, tagFragment: String) {
        requireActivity().supportFragmentManager.apply {
            beginTransaction()
                .replace(R.id.containerLayout, fragment, tagFragment)
                .commit()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = LayoutFragment()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}