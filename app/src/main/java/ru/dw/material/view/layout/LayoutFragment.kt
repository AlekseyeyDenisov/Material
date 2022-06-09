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
import ru.dw.material.utils.TAG_FRAGMENT_MOTION
import ru.dw.material.view.layout.constraint.ConstraintFragment
import ru.dw.material.view.layout.coordinator.CoordinatorFragment
import ru.dw.material.view.layout.motion.MotionFragment


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
                    launchFragment(ConstraintFragment.newInstance(), TAG_FRAGMENT_CONSTRAINT)
                    true
                }
                R.id.action_coordinator -> {
                    launchFragment(CoordinatorFragment.newInstance(), TAG_FRAGMENT_COORDINATOR)
                    true
                }
                R.id.action_motion -> {
                    launchFragment(MotionFragment.newInstance(), TAG_FRAGMENT_MOTION)
                    true
                }
                else -> {
                    true
                }
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.action_constrain
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
                    .add(R.id.containerLayout, fragment, tagFragment)
                    .addToBackStack(tagFragment)
                    .commit()
            }
        } else {
            requireActivity().supportFragmentManager
                .popBackStack(tagFragment, 0)
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