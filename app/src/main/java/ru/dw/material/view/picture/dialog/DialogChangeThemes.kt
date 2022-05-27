package ru.dw.material.view.picture.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import ru.dw.material.R

class DialogChangeThemes: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(activity)
            .setTitle(getString(R.string.theme_change))
            .setMessage(getString(R.string.theme_change_warning))
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                restartActivity()
                dialog.cancel()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }
            .create()
    }
    private fun restartActivity() {
        requireActivity().finish()
        startActivity(requireActivity().intent)
    }



}
