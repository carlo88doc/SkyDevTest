package it.carlo.skydevtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import it.carlo.skydevtest.R

class HomeFragment: Fragment(R.layout.fragment_home) {

    companion object{
        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment().apply {
            arguments = Bundle()
        }
    }
}