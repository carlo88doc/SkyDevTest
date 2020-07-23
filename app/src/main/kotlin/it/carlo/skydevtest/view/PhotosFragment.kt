package it.carlo.skydevtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import it.carlo.skydevtest.R

class PhotosFragment: Fragment(R.layout.fragment_photos) {

    companion object{
        const val TAG = "PhotosFragment"

        fun newInstance() = PhotosFragment().apply {
            arguments = Bundle()

        }
    }
}