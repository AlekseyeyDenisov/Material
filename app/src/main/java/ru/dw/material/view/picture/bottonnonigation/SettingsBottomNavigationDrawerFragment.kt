package ru.dw.material.view.picture.bottonnonigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.dw.material.R
import ru.dw.material.databinding.BottomSettingsNavigationLayoutBinding
import ru.dw.material.utils.CONSTANT_THEMES_BLU
import ru.dw.material.utils.CONSTANT_THEMES_GREEN
import ru.dw.material.utils.CONSTANT_THEMES_RED
import ru.dw.material.utils.SharedPreferencesManagerNasa
import ru.dw.material.view.picture.dialog.DialogChangeThemes


class SettingsBottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSettingsNavigationLayoutBinding? = null
    val binding: BottomSettingsNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSettingsNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationView.setNavigationItemSelectedListener {
            val pref = SharedPreferencesManagerNasa(requireContext())
            val dialog = DialogChangeThemes()

            val transaction: FragmentTransaction =
                (context as FragmentActivity)
                    .supportFragmentManager
                    .beginTransaction()


            when (it.itemId) {
                R.id.navigation_theme_red -> {
                    pref.setThemes(CONSTANT_THEMES_RED)
                    dialog.show(transaction, "")
                    dismiss()
                }
                R.id.navigation_theme_blue -> {
                    pref.setThemes(CONSTANT_THEMES_BLU)
                    dialog.show(transaction, "")
                    dismiss()
                }
                R.id.navigation_theme_green -> {
                    pref.setThemes(CONSTANT_THEMES_GREEN)
                    dialog.show(transaction, "")
                    dismiss()
                }
            }
            true
        }
    }




    companion object {
        @JvmStatic
        fun newInstance() = SettingsBottomNavigationDrawerFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}