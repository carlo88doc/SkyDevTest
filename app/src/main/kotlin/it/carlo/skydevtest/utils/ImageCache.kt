package it.carlo.skydevtest.utils

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.widget.ImageView
import com.squareup.picasso.*
import it.carlo.skydevtest.SkyDevApplication
import it.carlo.skydevtest.model.network.NetworkProvider

class ImageCache {

    companion object {

        private fun getMaxMemoryCacheSize(): Int {
            val am = SkyDevApplication.instance.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val hasLargeHeap = (SkyDevApplication.instance.applicationInfo.flags and ApplicationInfo.FLAG_LARGE_HEAP) != 0

            val memoryClass: Int
            memoryClass = if (hasLargeHeap) am.largeMemoryClass else am.memoryClass

            return 1024 * 1024 * memoryClass / 8
        }


        private var picasso:Picasso = Picasso.Builder(SkyDevApplication.instance)
                .memoryCache(LruCache(getMaxMemoryCacheSize()))
                .downloader(OkHttp3Downloader(NetworkProvider.httpClient))
                .build()


        fun loadImage(imageView: ImageView, imgPath: String?, placeholderResId:Int?) {
            loadImage(imageView, imgPath, null, placeholderResId)
        }

        fun loadImage(imageView: ImageView, imgPath: String?, callback: Callback?, placeholderResId:Int?) {
            val picassoRequest:RequestCreator?

            if (imgPath?.isNotEmpty()==true){
                picassoRequest = picasso.load(imgPath)

                if (placeholderResId!=null){
                    picassoRequest.placeholder(placeholderResId)
                }

                picassoRequest.into(imageView, callback)
            }else{
                if (placeholderResId!=null){
                    picassoRequest = picasso.load(placeholderResId)
                    picassoRequest.into(imageView, callback)
                }
            }
        }
    }

}

