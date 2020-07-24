package it.carlo.skydevtest.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import it.carlo.skydevtest.R
import it.carlo.skydevtest.model.data.RedditPhotoModelView
import it.carlo.skydevtest.utils.FORMAT_DATE_FULL
import it.carlo.skydevtest.utils.setHtmlText
import it.carlo.skydevtest.utils.toDateString
import kotlinx.android.synthetic.main.fragment_detail_description.*

class DetailDescriptionFragment: Fragment(R.layout.fragment_detail_description) {

    companion object{
        const val TAG = "DetailDescriptionFragment"
        private const val BUNDLE_KEY_ITEM = TAG.plus("BUNDLE_KEY_ITEM")

        private var item:RedditPhotoModelView? = null

        fun newInstance(item:RedditPhotoModelView) = DetailDescriptionFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_KEY_ITEM, item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item = requireArguments().getParcelable(BUNDLE_KEY_ITEM)

        item?.let {
            detailPhotoDescriptionTitleText.setHtmlText(it.title)
            detailPhotoAuthorText.text = it.author
            detailPhotoDateText.text = it.timestampCreated?.toDateString(FORMAT_DATE_FULL)
            detailPhotoDescriptionText.setHtmlText(if (it.description?.isNotEmpty()==true){
                it.description
            }else getString(R.string.description_placeholder))

        }

    }

}