package ru.dw.material.view.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dw.material.R
import ru.dw.material.databinding.FargmentAnimationBinding
import ru.dw.material.utils.TAG_FRAGMENT_CONSTRAINT
import ru.dw.material.view.animation.transition.TransitionFragment


class AnimationFragment : Fragment() {
    private var _binding: FargmentAnimationBinding? = null
    private val binding: FargmentAnimationBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FargmentAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigation()

    }

    private fun bottomNavigation() {

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_transition -> {
                    goToFragment(TransitionFragment.newInstance(), TAG_FRAGMENT_CONSTRAINT)
                    true
                }

                else -> {
                    true
                }
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.action_transition
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
        fun newInstance() = AnimationFragment()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}