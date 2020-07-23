package it.carlo.skydevtest.model.network

import io.reactivex.Single
import it.carlo.skydevtest.model.data.RedditResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/r/{placeholder}/top.json")
    fun getPhotos(@Path("placeholder") placeholder: String):Single<RedditResponse>

}