package it.carlo.skydevtest.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import it.carlo.skydevtest.R
import it.carlo.skydevtest.model.data.RedditPhotoModelView
import it.carlo.skydevtest.utils.ImageCache
import it.carlo.skydevtest.utils.setHtmlText
import kotlinx.android.synthetic.main.fragment_detail_photo.*

class DetailPhotoFragment: Fragment(R.layout.fragment_detail_photo) {

    companion object{
        const val TAG = "DetailPhotoFragment"
        private const val BUNDLE_KEY_ITEM = TAG.plus("BUNDLE_KEY_ITEM")

        private var item:RedditPhotoModelView? = null

        fun newInstance(item:RedditPhotoModelView) = DetailPhotoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_KEY_ITEM, item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item = requireArguments().getParcelable(BUNDLE_KEY_ITEM)

        item?.let {
            ImageCache.loadImage(detailPhotoImageView, it.fullImageUrl, R.drawable.item_photo_placeholder)
            detailPhotoTitleTextView.setHtmlText(it.title)
            detailPhotoLikeTextView.text = "${(it.voteRatio?:0.0)*100} %"
            detailPhotoCommentsTextView.text = it.numComments.toString()
        }

    }

}