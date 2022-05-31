package ru.dw.material.view.picture.novigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import ru.dw.material.R
import ru.dw.material.databinding.FragmentLowPictureBinding
import ru.dw.material.model.ResponseDataItemDay

const val KEY_BUNDLE_PICTURE_LOW = "KEY_BUNDLE_PICTURE_LOW"
class LowPictureFragment: Fragment() {
    private var _binding: FragmentLowPictureBinding? = null
    private val binding: FragmentLowPictureBinding get() = _binding!!
    private lateinit var day: ResponseDataItemDay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            day = it.getParcelable<ResponseDataItemDay>(KEY_BUNDLE_PICTURE_LOW) as ResponseDataItemDay
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLowPictureBinding.inflate(inflater, container, false)
        initLoadPicture()

        return binding.root
    }

    private fun initLoadPicture() {
        binding.lowPicture.load(day.url) {
            placeholder(R.drawable.loadig)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) =
            LowPictureFragment().apply {
                arguments = bundle
            }
    }
}