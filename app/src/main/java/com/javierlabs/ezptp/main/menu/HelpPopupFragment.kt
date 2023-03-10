package com.javierlabs.ezptp.main.menu

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.javierlabs.ezptp.R

class HelpPopupFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireActivity(), R.style.dialog)
            .setView(R.layout.main_fragment_help_popup)
            .create()
}
