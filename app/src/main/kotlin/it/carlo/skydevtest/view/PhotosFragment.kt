package it.carlo.skydevtest.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import it.carlo.skydevtest.R
import it.carlo.skydevtest.model.data.RedditPhotoModelView
import it.carlo.skydevtest.utils.ERROR_RESOURCE_EMPTY
import it.carlo.skydevtest.utils.getStringResourceFromErrorCode
import it.carlo.skydevtest.utils.getTypedText
import it.carlo.skydevtest.viewmodel.MainViewModel
import it.carlo.skydevtest.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.fragment_photos.*

class PhotosFragment: Fragment(R.layout.fragment_photos) {

    private lateinit var viewModel:PhotosViewModel
    private lateinit var activityViewModel:MainViewModel


    companion object{
        const val TAG = "PhotosFragment"

        fun newInstance() = PhotosFragment().apply {
            arguments = Bundle()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        activityViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.photosLiveData.observe(viewLifecycleOwner, Observer {
            fillRecyclerView(it)
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            showPlaceholderView(R.drawable.ic_error, getStringResourceFromErrorCode(it))
        })

        photosSearchImageButton.setOnClickListener {
            val query = photosSearchEditText.getTypedText()
            if (query.isEmpty()){
                showPlaceholderView(R.drawable.ic_search_off, getStringResourceFromErrorCode(ERROR_RESOURCE_EMPTY))
            }else{
                viewModel.searchRedditPhotos(activityViewModel.progressLiveData, photosSearchEditText.getTypedText())
            }
        }
    }

    private fun showPlaceholderView(resIcon:Int, resMessage:Int){
        photosRecyclerView.visibility = View.GONE
        photosPlaceholderView.visibility = View.VISIBLE

        photosPlaceholderIcon.setImageResource(resIcon)
        photosPlaceholderText.setText(resMessage)
    }


    private fun fillRecyclerView(data:ArrayList<RedditPhotoModelView>){
        if (data.isEmpty()){
            showPlaceholderView(R.drawable.ic_search_off, getStringResourceFromErrorCode(ERROR_RESOURCE_EMPTY))
        }else{
            photosPlaceholderView.visibility = View.GONE
            photosRecyclerView.visibility = View.VISIBLE
        }
    }
}