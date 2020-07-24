package it.carlo.skydevtest.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import it.carlo.skydevtest.R
import it.carlo.skydevtest.model.data.RedditPhotoModelView
import it.carlo.skydevtest.view.adapter.PhotoDetailPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : BaseActivity() {

    private var pagerCallback = object:ViewPager2.OnPageChangeCallback(){

        override fun onPageSelected(position: Int) {
            indicator1.alpha = if (position==0) 1f else 0.5f
            indicator2.alpha = if (position==1) 1f else 0.5f
            indicator3.alpha = if (position==2) 1f else 0.5f
            super.onPageSelected(position)
        }
    }

    companion object{
        private const val INTENT_KEY_PHOTO_MODEL = "INTENT_KEY_PHOTO_MODEL"

        fun start(context: Context, item:RedditPhotoModelView) {
            val starter = Intent(context, DetailActivity::class.java)
            starter.putExtra(INTENT_KEY_PHOTO_MODEL, item)
            starter.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.extras?.let {bundle ->
            bundle.getParcelable<RedditPhotoModelView>(INTENT_KEY_PHOTO_MODEL)?.let {item ->
                initViewPager(item)
            }
        }
    }

    private fun initViewPager(item:RedditPhotoModelView){
        viewPager.adapter = PhotoDetailPagerAdapter(this, item)
        viewPager.registerOnPageChangeCallback(pagerCallback)
    }

    override fun onDestroy() {
        viewPager?.unregisterOnPageChangeCallback(pagerCallback)
        super.onDestroy()

    }

}
