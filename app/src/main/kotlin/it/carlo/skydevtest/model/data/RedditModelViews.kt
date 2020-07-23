package it.carlo.skydevtest.model.data


class RedditPhotoModelView{
    var id:String? = null
    var author:String? = null
    var title:String? = null
    var voteRatio:Double? = null
    var thumbnailUrl:String? = null
    var fullImageUrl:String? = null
    var alternativeImageUrl:String? = null
    var hint:String? = null
    var timestampCreated:Long? = null
    var isOver18:Boolean = false
    var numComments:Int = 0
    var permalink:String? = null
    var subredditSubscribers:Int = 0
}
