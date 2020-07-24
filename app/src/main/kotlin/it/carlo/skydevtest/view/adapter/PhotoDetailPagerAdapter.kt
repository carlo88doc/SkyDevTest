package it.carlo.skydevtest.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import it.carlo.skydevtest.model.data.RedditPhotoModelView
import it.carlo.skydevtest.view.DetailDescriptionFragment
import it.carlo.skydevtest.view.DetailPhotoFragment
import it.carlo.skydevtest.view.DetailWebViewFragment

class PhotoDetailPagerAdapter(activity:FragmentActivity, val model:RedditPhotoModelView): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> DetailPhotoFragment.newInstance(model)
            1 -> DetailDescriptionFragment.newInstance(model)
            2 -> DetailWebViewFragment.newInstance(model.permalink)
            else -> DetailPhotoFragment.newInstance(model)
        }
    }

}
