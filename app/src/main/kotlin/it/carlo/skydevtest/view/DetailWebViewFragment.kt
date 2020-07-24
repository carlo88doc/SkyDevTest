package it.carlo.skydevtest.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import it.carlo.skydevtest.R
import kotlinx.android.synthetic.main.fragment_detail_webview.*

class DetailWebViewFragment: Fragment(R.layout.fragment_detail_webview) {

    companion object{
        const val TAG = "DetailWebViewFragment"
        private const val BUNDLE_KEY_URL = TAG.plus("BUNDLE_KEY_URL")

        fun newInstance(url:String?) = DetailWebViewFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_KEY_URL, url)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireArguments().getString(BUNDLE_KEY_URL)?.let {
            detailPhotoWebView.loadUrl(it)
        }


    }

}