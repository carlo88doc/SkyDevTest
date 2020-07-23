package it.carlo.skydevtest.model.data

import io.reactivex.functions.Function


fun mapRedditResponseToRedditPhotoModelViews() = Function<RedditResponse, ArrayList<RedditPhotoModelView>> { resp ->

    return@Function arrayListOf<RedditPhotoModelView>().apply {
        resp.data?.children?.forEach{
            it.data?.let {photoResponse ->
                var modelView = RedditPhotoModelView()
                modelView.alternativeImageUrl = photoResponse.alternativeImageUrl
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