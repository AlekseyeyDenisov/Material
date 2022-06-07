package ru.dw.material.view.animation.transition

import android.os.Bundle
import android.view.Gravity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.*
import ru.dw.material.databinding.FragmentTransitionBinding

class TransitionFragment : Fragment() {


    private var _binding: FragmentTransitionBinding? = null
    private val binding: FragmentTransitionBinding
        get() = _binding!!
    private var isOpen: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransitionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            //TransitionManager.beginDelayedTransition(binding.transitionsContainer)//все делает за меня
            val transitionSlide = Slide(Gravity.END)
            transitionSlide.duration = 2000
            val transitionFade = Fade()
            transitionFade.duration = 2000
            val transitionBounds = ChangeBounds()
            transitionBounds.duration = 2000

            val transitionSet = TransitionSet()
            transitionSet.addTransition(transitionSlide)
            transitionSet.addTransition(transitionFade)
            transitionSet.addTransition(transitionBounds)

            TransitionManager.beginDelayedTransition(binding.transitionsContainer,transitionSet)
            isOpen = !isOpen
            binding.text.visibility = if (isOpen) {
                View.VISIBLE
            } else {
                View.GONE
            }



        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = TransitionFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
