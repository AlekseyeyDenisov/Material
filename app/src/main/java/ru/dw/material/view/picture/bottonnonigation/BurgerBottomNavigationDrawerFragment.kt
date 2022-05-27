package ru.dw.material.view.picture.bottonnonigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.dw.material.R
import ru.dw.material.databinding.BottomBurgerNavigationLayoutBinding


class BurgerBottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomBurgerNavigationLayoutBinding? = null
    val binding: BottomBurgerNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomBurgerNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_one -> {
                    Log.d("@@@", "На экран 1")
                    dismiss()
                }
                R.id.navigation_two -> {
                    Log.d("@@@", "На экран 2")
                    dismiss()
                }
            }
            true
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = BurgerBottomNavigationDrawerFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}