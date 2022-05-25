package ru.dw.material.view.picture

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import ru.dw.material.databinding.FragmentPictureOfTheDayBinding
import ru.dw.material.view.PictureOfTheDayAppState


class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding
        get() = _binding!!
    private val viewModel: PictureOfTheDayFragmentViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayFragmentViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        chipGroup()

    }

    private fun chipGroup() {
        binding.today.setOnClickListener {
            viewModel.sendRequest(0)
        }
        binding.yesterday.setOnClickListener {
            viewModel.sendRequest(1)
        }
        binding.tdby.setOnClickListener {
            viewModel.sendRequest(2)
        }
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.sendRequest(0)
    }

    private fun renderData(pictureOfTheDayAppState: PictureOfTheDayAppState) {
        when (pictureOfTheDayAppState) {
            is PictureOfTheDayAppState.Loading -> {
                visibilityLoading(true)

            }
            is PictureOfTheDayAppState.Error -> {
                visibilityLoading(false)
                Log.d("@@@", "renderData Error: ${pictureOfTheDayAppState.error}")
                Toast.makeText(requireContext(), pictureOfTheDayAppState.error, Toast.LENGTH_SHORT).show()

            }
            is PictureOfTheDayAppState.Success -> {
                visibilityLoading(false)
                binding.imageView.load(pictureOfTheDayAppState.responseDataItemDay.url)
            }
        }
    }

    private fun visibilityLoading(visibility:Boolean){
        if (visibility) binding.loadingPicture.visibility = View.VISIBLE
        else binding.loadingPicture.visibility = View.GONE
    }


    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}