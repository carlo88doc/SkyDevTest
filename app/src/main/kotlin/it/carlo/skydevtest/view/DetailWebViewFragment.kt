package it.carlo.skydevtest.view

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
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

        detailPhotoWebViewProgressBar.visibility = View.VISIBLE

        requireArguments().getString(BUNDLE_KEY_URL)?.let {
            initWebView(it)
        }
    }

    private fun initWebView(url:String){
        detailPhotoWebView.webViewClient = object :WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                detailPhotoWebViewProgressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                detailPhotoWebViewProgressBar.visibility = View.GONE
            }
        }
        detailPhotoWebView.loadUrl(url)
    }

}