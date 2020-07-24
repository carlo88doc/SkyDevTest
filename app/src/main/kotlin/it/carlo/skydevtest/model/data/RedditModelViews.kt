package it.carlo.skydevtest.model.data

import android.os.Parcel
import android.os.Parcelable

class RedditPhotoModelView() :Parcelable{
    var id:String? = null
    var author:String? = null
    var title:String? = null
    var description:String? = null
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

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        author = parcel.readString()
        title = parcel.readString()
        description = parcel.readString()
        voteRatio = parcel.readValue(Double::class.java.classLoader) as? Double
        thumbnailUrl = parcel.readString()
        fullImageUrl = parcel.readString()
        alternativeImageUrl = parcel.readString()
        hint = parcel.readString()
        timestampCreated = parcel.readValue(Long::class.java.classLoader) as? Long
        isOver18 = parcel.readByte() != 0.toByte()
        numComments = parcel.readInt()
        permalink = parcel.readString()
        subredditSubscribers = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeValue(voteRatio)
        parcel.writeString(thumbnailUrl)
        parcel.writeString(fullImageUrl)
        parcel.writeString(alternativeImageUrl)
        parcel.writeString(hint)
        parcel.writeValue(timestampCreated)
        parcel.writeByte(if (isOver18) 1 else 0)
        parcel.writeInt(numComments)
        parcel.writeString(permalink)
        parcel.writeInt(subredditSubscribers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RedditPhotoModelView> {
        override fun createFromParcel(parcel: Parcel): RedditPhotoModelView {
            return RedditPhotoModelView(parcel)
        }

        override fun newArray(size: Int): Array<RedditPhotoModelView?> {
            return arrayOfNulls(size)
        }
    }

}
