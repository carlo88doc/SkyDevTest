package it.carlo.skydevtest.model.network

import io.reactivex.Single
import it.carlo.skydevtest.model.data.RedditResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/r/photos/top.json")
    fun getPhotos():Single<RedditResponse>

}