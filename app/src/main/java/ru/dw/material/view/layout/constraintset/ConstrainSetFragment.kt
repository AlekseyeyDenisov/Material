package ru.dw.material.view.layout.constraintset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import ru.dw.material.R
import ru.dw.material.databinding.FragmentAnimationsBonusStartBinding

class ConstrainSetFragment : Fragment() {


    private var _binding: FragmentAnimationsBonusStartBinding? = null
    private val binding: FragmentAnimationsBonusStartBinding
        get() = _binding!!
    private var isOpen = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimationsBonusStartBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //constrainSet.clone(binding.constraintContainer)
        binding.tap.setOnClickListener {
            isOpen = !isOpen
            val constrainSet = ConstraintSet()
            constrainSet.clone(binding.constraintContainer)

            val transition = ChangeBounds()
            transition.interpolator = AnticipateInterpolator(5F)
            transition.duration = 1000
            TransitionManager.beginDelayedTransition(binding.constraintContainer,transition)
//            constrainSet.clone(requireContext(), R.layout.fragment_animations_bonus_end)

            if (isOpen){
                //constrainSet.clone(requireContext(), R.layout.fragment_animations_bonus_end)
               // constrainSet.clear(R.id.title)
                constrainSet.connect(R.id.title,ConstraintSet.END,R.id.backgroundImage,ConstraintSet.END)
            }else {
                constrainSet.connect(R.id.title,ConstraintSet.END,R.id.backgroundImage,ConstraintSet.START)
                //constrainSet.clone(requireContext(), R.layout.fragment_animations_bonus_start)
            }
            constrainSet.applyTo(binding.constraintContainer)
        }



    }


    companion object {
        @JvmStatic
        fun newInstance() = ConstrainSetFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
