package ru.dw.material.view.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dw.material.databinding.FargmentLayoutBinding
import ru.dw.material.view.main.MainActivity


class LayoutFragment : Fragment() {
    private var _binding: FargmentLayoutBinding? = null
    private val binding: FargmentLayoutBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FargmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomBarActivity()

    }




    companion object {
        @JvmStatic
        fun newInstance() = LayoutFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onDestroyView() {
        super.onDestroyView()
        val activity = activity as MainActivity?
        activity?.hideBottomBar(false)
    }
    private fun hideBottomBarActivity() {
        val activity = activity as MainActivity?
        activity?.hideBottomBar(true)
    }
}