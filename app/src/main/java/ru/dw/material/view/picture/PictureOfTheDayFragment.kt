package ru.dw.material.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.dw.material.R
import ru.dw.material.databinding.FragmentPictureOfTheDayBinding


class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding:FragmentPictureOfTheDayBinding
    get() = _binding!!
    private val viewModel:PictureOfTheDayFragmentViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayFragmentViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater,container,false)
        return binding.root
    }



    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheDayFragment()
    }
}