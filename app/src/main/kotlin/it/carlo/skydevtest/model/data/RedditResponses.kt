package it.carlo.skydevtest.model.data

import com.squareup.moshi.Json

data class RedditResponse(
    @Json(name= "kind")
    val kind:String? = null,
    @Json(name= "data")
    val data:RedditDataResponse? = null
)

data class RedditDataResponse(
    @Json(name= "modhash")
    val modhash:String? = null,
    @Json(name= "dist")
    val dist:Int? = null,
    @Json(name= "children")
    val children:List<RedditDetailDataResponse>? = null
)

data class RedditDetailDataResponse(
    @Json(name= "kind")
    val kind:String? = null,
    @Json(name= "data")
    val data:RedditPhotoResponse? = null
)

data class RedditPhotoResponse(
    @Json(name= "id")
    val id:String? = null,
    @Json(name= "author")
    val author:String? = null,
    @Json(name= "title")
    val title:String? = null,
    @Json(name= "upvote_ratio")
    val voteRatio:Double? = null,
    @Json(name= "thumbnail")
    val thumbnailUrl:String? = null,
    @Json(name= "url")
    val fullImageUrl:String? = null,
    @Json(name= "url_overridden_by_dest")
    val alternativeImageUrl:String? = null,
    @Json(name= "post_hint")
    val hint:String? = null,
    @Json(name= "created")
    val timestampCreated:Long? = null,
    @Json(name= "over_18")
    val isOver18:Boolean = false,
    @Json(name= "num_comments")
    val numComments:Int = 0,
    @Json(name= "permalink")
    val permalink:String? = null,
    @Json(name= "subreddit_subscribers")
    val subredditSubscribers:Int = 0
)
