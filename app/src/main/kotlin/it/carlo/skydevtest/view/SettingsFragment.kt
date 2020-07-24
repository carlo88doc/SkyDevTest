package it.carlo.skydevtest.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import it.carlo.skydevtest.R
import it.carlo.skydevtest.viewmodel.MainViewModel
import it.carlo.skydevtest.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment: Fragment(R.layout.fragment_settings) {

    lateinit var viewModel:SettingsViewModel
    lateinit var activityViewModel:MainViewModel

    companion object{
        const val TAG = "SettingsFragment"

        fun newInstance() = SettingsFragment().apply {
            arguments = Bundle()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        activityViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.getOver18LiveData(activityViewModel.progressLiveData).observe(viewLifecycleOwner, Observer {
            switchSettingsOver18.setOnCheckedChangeListener(null)
            switchSettingsOver18.isChecked = it
            addSwitchChangeListener()
        })
    }


    private fun addSwitchChangeListener(){
        switchSettingsOver18.setOnCheckedChangeListener { _, checked ->
            viewModel.blockOver18Contents(activityViewModel.progressLiveData, checked)
        }
    }

}