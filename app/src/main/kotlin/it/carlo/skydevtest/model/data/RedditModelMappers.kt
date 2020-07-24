package it.carlo.skydevtest.model.data

import io.reactivex.functions.Function


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
                modelView.thumbnailUrl = photoResponse.thumbnailUrl
                modelView.fullImageUrl = photoResponse.fullImageUrl
                modelView.alternativeImageUrl = photoResponse.alternativeImageUrl
                modelView.hint = photoResponse.hint
                modelView.timestampCreated = photoResponse.timestampCreated
                modelView.isOver18 = photoResponse.isOver18
                modelView.numComments = photoResponse.numComments
                modelView.permalink = photoResponse.permalink
                modelView.subredditSubscribers = photoResponse.subredditSubscribers
                this.add(modelView)
            }
        }

    }
}