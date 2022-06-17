package ru.dw.material.view.animation.zoom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import ru.dw.material.databinding.FragmentZoomImagesBinding


class ZoomImagesFragment : Fragment() {


    private var _binding: FragmentZoomImagesBinding? = null
    private val binding: FragmentZoomImagesBinding
        get() = _binding!!
    private var isOpen: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZoomImagesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setOnClickListener {
            isOpen = !isOpen
            val transitionChangeBounds = ChangeBounds()
            transitionChangeBounds.duration = 3000
            val transitionImageTransform = ChangeImageTransform()
            transitionImageTransform.duration = 3000

            val transitionSet = TransitionSet()
            transitionSet.addTransition(transitionChangeBounds)
            transitionSet.addTransition(transitionImageTransform)


            TransitionManager.beginDelayedTransition(binding.root,transitionSet)

            binding.imageView.scaleType = if (isOpen)
            {ImageView.ScaleType.CENTER_CROP}else {ImageView.ScaleType.CENTER_INSIDE}

            val params = (binding.imageView.layoutParams as FrameLayout.LayoutParams)
            params.height = if (isOpen){FrameLayout.LayoutParams.MATCH_PARENT}else{FrameLayout.LayoutParams.WRAP_CONTENT}
            binding.imageView.layoutParams = params


        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = ZoomImagesFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
