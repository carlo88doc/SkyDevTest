package it.carlo.skydevtest.model.data

import io.reactivex.functions.Function
import it.carlo.skydevtest.BuildConfig


fun mapRedditResponseToRedditPhotoModelViews(blockAdultContents:Boolean) = Function<RedditResponse, ArrayList<RedditPhotoModelView>> { resp ->

    return@Function arrayListOf<RedditPhotoModelView>().apply {

        val filteredChildren = resp.data?.children?.filter {
            if (blockAdultContents){
                it.data?.isOver18 == false
            }else true
        }

        filteredChildren?.forEach{
            it.data?.let {photoResponse ->
                var modelView = RedditPhotoModelView()
                modelView.id = photoResponse.id
                modelView.author = photoResponse.author
                modelView.title = photoResponse.title
                modelView.voteRatio = photoResponse.voteRatio
                modelView.hint = photoResponse.hint
                modelView.timestampCreated = (photoResponse.timestampCreated ?: 0)*1000
                modelView.isOver18 = photoResponse.isOver18
                modelView.numComments = photoResponse.numComments
                modelView.permalink = "${BuildConfig.BASE_URL}${photoResponse.permalink}"
                modelView.subredditSubscribers = photoResponse.subredditSubscribers
                modelView.fullImageUrl = photoResponse.fullImageUrl
                modelView.alternativeImageUrl = photoResponse.alternativeImageUrl

                modelView.thumbnailUrl = if (photoResponse.thumbnailUrl?.isNotEmpty()==true){
                    photoResponse.thumbnailUrl
                }else{
                    if (modelView.fullImageUrl?.isNotEmpty()==true){
                        photoResponse.fullImageUrl
                    }else{
                        photoResponse.alternativeImageUrl
                    }
                }

                modelView.description = if (photoResponse.selfText?.isNotEmpty()==true){
                    photoResponse.selfText
                }else{
                    photoResponse.selfTextHtml
                }

                this.add(modelView)
            }
        }

    }
}