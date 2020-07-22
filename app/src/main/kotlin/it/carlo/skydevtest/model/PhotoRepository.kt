package it.carlo.skydevtest.model

import io.reactivex.Single
import it.carlo.skydevtest.model.network.NetworkProvider
import it.carlo.skydevtest.model.data.RedditResponse

class PhotoRepository {

    companion object{
        fun getPhotosSingle():Single<RedditResponse>{
            return NetworkProvider.getPhotos()
        }
    }
}