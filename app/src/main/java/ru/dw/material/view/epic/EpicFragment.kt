package ru.dw.material.view.epic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dw.material.databinding.FragmentEpicBinding
import ru.dw.material.repository.PictureOfTheDayRetrofitImpl


class EpicFragment : Fragment() {
    private var _binding: FragmentEpicBinding? = null
    private val binding: FragmentEpicBinding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PictureOfTheDayRetrofitImpl.getEpic()
    }

    companion object {
        @JvmStatic
        fun newInstance() = EpicFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}