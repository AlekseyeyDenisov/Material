package ru.dw.material.view.nasa.pictureoftheday.novigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import ru.dw.material.R
import ru.dw.material.databinding.FragmentPictureBinding
import ru.dw.material.dto.ResponseDataItemDay



class LowPictureFragment : Fragment() {
    private var _binding: FragmentPictureBinding? = null
    private val binding: FragmentPictureBinding get() = _binding!!
    private lateinit var day: ResponseDataItemDay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            day =
                it.getParcelable<ResponseDataItemDay>(KEY_BUNDLE_PICTURE_LOW) as ResponseDataItemDay
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        initLoadPicture()

        return binding.root
    }

    private fun initLoadPicture() {
        binding.picture.load(day.url) {
            placeholder(R.drawable.loadig)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val KEY_BUNDLE_PICTURE_LOW = "KEY_BUNDLE_PICTURE_LOW"
        @JvmStatic
        fun newInstance(bundle: Bundle) =
            LowPictureFragment().apply {
                arguments = bundle
            }


        fun bundle(responseDataItemDay: ResponseDataItemDay):Bundle{
            val bundle = Bundle()
            bundle.putParcelable(KEY_BUNDLE_PICTURE_LOW,responseDataItemDay)
            return bundle
        }
    }
}