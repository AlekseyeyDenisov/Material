package ru.dw.material.view.animation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dw.material.R
import ru.dw.material.databinding.FargmentAnimationBinding
import ru.dw.material.utils.TAG_FRAGMENT_EXPLODE
import ru.dw.material.utils.TAG_FRAGMENT_OBJECT_ANIM
import ru.dw.material.utils.TAG_FRAGMENT_TRANSITION
import ru.dw.material.utils.TAG_FRAGMENT_ZOOM
import ru.dw.material.view.animation.explode.ExplodeFragment
import ru.dw.material.view.animation.objectanimation.ObjectAnimationFragment
import ru.dw.material.view.animation.transition.TransitionFragment
import ru.dw.material.view.animation.zoom.ZoomImagesFragment


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
                    launchFragment(TransitionFragment.newInstance(), TAG_FRAGMENT_TRANSITION)
                    true
                }
                R.id.action_explode -> {
                    launchFragment(ExplodeFragment.newInstance(), TAG_FRAGMENT_EXPLODE)
                    true
                }
                R.id.action_zoom -> {
                    launchFragment(ZoomImagesFragment.newInstance(), TAG_FRAGMENT_ZOOM)
                    true
                }
                R.id.action_object -> {
                    launchFragment(ObjectAnimationFragment.newInstance(), TAG_FRAGMENT_OBJECT_ANIM)
                    true
                }
                else -> {
                    true
                }
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.action_object
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
        fun newInstance() = AnimationFragment()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}