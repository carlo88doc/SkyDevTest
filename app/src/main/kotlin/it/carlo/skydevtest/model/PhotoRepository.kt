package it.carlo.skydevtest.model

import io.reactivex.Single
import it.carlo.skydevtest.model.data.RedditPhotoModelView
import it.carlo.skydevtest.model.data.mapRedditResponseToRedditPhotoModelViews
import it.carlo.skydevtest.model.network.NetworkProvider

class PhotoRepository {

    companion object{

        fun getPhotosSingle(placeholder:String):Single<ArrayList<RedditPhotoModelView>>{

            return DiskRepository.getOver18Single().flatMap {blockAdultContents ->
                NetworkProvider.getPhotos(placeholder).map(mapRedditResponseToRedditPhotoModelViews(blockAdultContents))
            }
        }
    }
}