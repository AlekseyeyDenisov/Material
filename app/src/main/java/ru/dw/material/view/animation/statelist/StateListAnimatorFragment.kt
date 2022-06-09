package ru.dw.material.view.animation.statelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dw.material.databinding.FragmentStateListAnimatorBinding

class StateListAnimatorFragment : Fragment() {


    private var _binding: FragmentStateListAnimatorBinding? = null
    private val binding: FragmentStateListAnimatorBinding
        get() = _binding!!
    private var isOpen = false
    private val duration = 1500L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStateListAnimatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            binding.header.isSelected = binding.scrollView.canScrollVertically(-1)

        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = StateListAnimatorFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}




