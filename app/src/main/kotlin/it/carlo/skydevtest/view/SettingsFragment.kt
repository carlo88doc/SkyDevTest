package it.carlo.skydevtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import it.carlo.skydevtest.R

class SettingsFragment: Fragment(R.layout.fragment_settings) {

    companion object{
        const val TAG = "SettingsFragment"

        fun newInstance() = SettingsFragment().apply {
            arguments = Bundle()
        }
    }
}