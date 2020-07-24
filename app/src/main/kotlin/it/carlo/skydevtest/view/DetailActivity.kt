package it.carlo.skydevtest.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import it.carlo.skydevtest.R
import it.carlo.skydevtest.model.data.RedditPhotoModelView
import it.carlo.skydevtest.view.adapter.PhotoDetailPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : BaseActivity() {

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
    }

}
