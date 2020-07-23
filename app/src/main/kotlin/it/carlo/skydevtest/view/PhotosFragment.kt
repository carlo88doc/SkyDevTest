package it.carlo.skydevtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import it.carlo.skydevtest.R
import it.carlo.skydevtest.viewmodel.PhotosViewModel

class PhotosFragment: Fragment(R.layout.fragment_photos) {

    lateinit var viewModel:PhotosViewModel

    companion object{
        const val TAG = "PhotosFragment"

        fun newInstance() = PhotosFragment().apply {
            arguments = Bundle()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
    }


}