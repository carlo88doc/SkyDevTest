package it.carlo.skydevtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import it.carlo.skydevtest.R
import it.carlo.skydevtest.viewmodel.SettingsViewModel

class SettingsFragment: Fragment(R.layout.fragment_settings) {

    lateinit var viewModel:SettingsViewModel

    companion object{
        const val TAG = "SettingsFragment"

        fun newInstance() = SettingsFragment().apply {
            arguments = Bundle()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

}