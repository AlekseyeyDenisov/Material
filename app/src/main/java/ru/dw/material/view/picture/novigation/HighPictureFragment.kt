package ru.dw.material.view.picture.novigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import ru.dw.material.R
import ru.dw.material.databinding.FragmentHighPictureBinding
import ru.dw.material.model.ResponseDataItemDay

const val KEY_BUNDLE_PICTURE_HIGH = "KEY_BUNDLE_PICTURE_HIGH"
class HighPictureFragment: Fragment() {
    private var _binding:FragmentHighPictureBinding? = null
    private val binding:FragmentHighPictureBinding get() = _binding!!
    private lateinit var day: ResponseDataItemDay


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            day = it.getParcelable<ResponseDataItemDay>(KEY_BUNDLE_PICTURE_HIGH) as ResponseDataItemDay
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHighPictureBinding.inflate(inflater,container,false)
        initLoadPicture()
        return binding.root
    }
    private fun initLoadPicture() {
        binding.highPicture.load(day.hdUrl){
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
            HighPictureFragment().apply {
                arguments = bundle
            }
    }
}