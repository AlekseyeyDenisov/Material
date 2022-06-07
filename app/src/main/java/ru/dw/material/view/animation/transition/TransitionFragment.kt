package ru.dw.material.view.animation.transition

import android.os.Bundle
import android.view.Gravity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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
            isOpen = !isOpen
            //TransitionManager.beginDelayedTransition(binding.transitionsContainer)//все делает за меня
            val transitionSlide = Slide(Gravity.END)
            transitionSlide.duration = 2000
            val transitionFade = Fade()
            transitionFade.duration = 2000
            val transitionBounds = ChangeBounds()
            transitionBounds.duration = 2000

            val path = ArcMotion()

            transitionBounds.setPathMotion(path)

            val transitionSet = TransitionSet()
            transitionSet.addTransition(transitionSlide)
            transitionSet.addTransition(transitionFade)
            transitionSet.addTransition(transitionBounds)

            TransitionManager.beginDelayedTransition(binding.transitionsContainer,transitionSet)
            val parmsButtom = (binding.button.layoutParams as FrameLayout.LayoutParams)

             if (isOpen) {
                 binding.text.visibility =  View.VISIBLE
                 parmsButtom.gravity = Gravity.BOTTOM or Gravity.END
            } else {
                 binding.text.visibility =   View.GONE
                 parmsButtom.gravity = Gravity.TOP or Gravity.START
            }
            binding.button.layoutParams = parmsButtom



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
