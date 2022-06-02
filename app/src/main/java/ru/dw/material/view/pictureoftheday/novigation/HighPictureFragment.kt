package ru.dw.material.view.pictureoftheday.novigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import ru.dw.material.R
import ru.dw.material.databinding.FragmentHighPictureBinding
import ru.dw.material.dto.ResponseDataItemDay



class HighPictureFragment : Fragment() {

    private var _binding: FragmentHighPictureBinding? = null
    private val binding: FragmentHighPictureBinding get() = _binding!!
    private lateinit var day: ResponseDataItemDay


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            day =
                it.getParcelable<ResponseDataItemDay>(KEY_BUNDLE_PICTURE_HIGH) as ResponseDataItemDay
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHighPictureBinding.inflate(inflater, container, false)
        initLoadPicture()
        return binding.root
    }

    private fun initLoadPicture() {
        binding.highPicture.load(day.hdUrl) {
            placeholder(R.drawable.loadig)
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val KEY_BUNDLE_PICTURE_HIGH = "KEY_BUNDLE_PICTURE_HIGH"

        @JvmStatic
        fun newInstance(bundle: Bundle) =
            HighPictureFragment().apply {
                arguments = bundle
            }

        fun bundle(responseDataItemDay: ResponseDataItemDay): Bundle {
            val bundle = Bundle()
            bundle.putParcelable(KEY_BUNDLE_PICTURE_HIGH, responseDataItemDay)
            return bundle
        }
    }
}