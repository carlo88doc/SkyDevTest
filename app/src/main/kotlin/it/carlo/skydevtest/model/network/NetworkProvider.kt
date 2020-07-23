package it.carlo.skydevtest.model.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import it.carlo.skydevtest.BuildConfig
import it.carlo.skydevtest.SkyDevApplication
import it.carlo.skydevtest.model.data.RedditResponse
import it.carlo.skydevtest.model.network.utils.addCacheInterceptor
import it.carlo.skydevtest.model.network.utils.addLoggingInterceptor
import it.carlo.skydevtest.model.network.utils.trustAllCertificates
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.Executors

class NetworkProvider {

    companion object{
        private const val CACHE_SIZE_MB = 10L
        private const val CACHE_VALIDITY_DAYS = 7

        val httpClient:OkHttpClient

        init {

            val httpClientBuilder = OkHttpClient.Builder()
                .addLoggingInterceptor()
                .trustAllCertificates()
                .addCacheInterceptor(SkyDevApplication.instance, CACHE_VALIDITY_DAYS, CACHE_SIZE_MB)

            httpClient = httpClientBuilder.build()

            RxJavaPlugins.setErrorHandler { t: Throwable -> Log.e("NetworkProvider", "Unmanaged error", t) }
        }

        private val api: ApiService = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()).asLenient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .callbackExecutor(Executors.newCachedThreadPool())
            .client(httpClient)
            .build()
            .create(ApiService::class.java)

        fun getPhotos(placeholder: String): Single<RedditResponse> {
            return api.getPhotos(placeholder)
        }

    }
}