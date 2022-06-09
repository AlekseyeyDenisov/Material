package ru.dw.material.view.animation.objectanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dw.material.databinding.FragmentObjectAnimationBinding

class ObjectAnimationFragment : Fragment() {


    private var _binding: FragmentObjectAnimationBinding? = null
    private val binding: FragmentObjectAnimationBinding
        get() = _binding!!
    private var isOpen = false
    private val duration = 1500L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentObjectAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            isOpen = !isOpen
            if (isOpen) {
                ObjectAnimator.ofFloat(binding.fab, View.ROTATION, 0F, 135F)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, -250F)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, -130F)
                    .setDuration(duration).start()

                binding.optionOneContainer.animate().alpha(1F).setDuration(duration)
                    .setListener(object :AnimatorListenerAdapter(){
                    override fun onAnimationEnd(p0: Animator?) {
                        binding.optionOneContainer.isClickable = true
                        binding.optionTwoContainer.isClickable = true
                    }

                })
                binding.optionTwoContainer.animate().alpha(1F).setDuration(duration)
                    .setListener(object :AnimatorListenerAdapter(){
                        override fun onAnimationEnd(p0: Animator?) {
                            binding.optionTwoContainer.isClickable = true
                        }

                    })
                binding.transparentBackground.animate().alpha(0.5F).setDuration(duration)
                    .setListener(object :AnimatorListenerAdapter(){
                        override fun onAnimationEnd(p0: Animator?) {
                            binding.optionTwoContainer.isClickable = true
                        }

                    })

            } else {
                ObjectAnimator.ofFloat(binding.fab, View.ROTATION, 135F, 0F)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, 0F)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, 0F)
                    .setDuration(duration).start()

                binding.optionOneContainer.animate().alpha(0F).setDuration(duration)
                    .setListener(object :AnimatorListenerAdapter(){
                        override fun onAnimationEnd(p0: Animator?) {
                            binding.optionOneContainer.isClickable = false

                        }

                    })
                binding.optionTwoContainer.animate().alpha(0F).setDuration(duration)
                    .setListener(object :AnimatorListenerAdapter(){
                        override fun onAnimationEnd(p0: Animator?) {
                            binding.optionTwoContainer.isClickable = false
                        }

                    })
                binding.transparentBackground.animate().alpha(0F).setDuration(duration)
                    .setListener(object :AnimatorListenerAdapter(){
                        override fun onAnimationEnd(p0: Animator?) {
                            binding.optionTwoContainer.isClickable = false
                        }

                    })
            }
        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = ObjectAnimationFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}




