package it.carlo.skydevtest.model.network.utils

import android.content.Context
import it.carlo.skydevtest.BuildConfig
import it.carlo.skydevtest.utils.DeviceConnection
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

private const val HEADER_CACHE_CONTROL = "Cache-Control"
private const val HEADER_PRAGMA = "Pragma"

class NetworkException(val statusCode:Int):Exception()

fun OkHttpClient.Builder.trustAllCertificates():OkHttpClient.Builder{

    val trustAllCerts = arrayOf<TrustManager>(
        object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>?, authType: String?) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    )

    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, SecureRandom())
    val sslSocketFactory = sslContext.socketFactory


    this.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
    this.hostnameVerifier { _, _ -> true }

    return this
}

fun OkHttpClient.Builder.addLoggingInterceptor():OkHttpClient.Builder{
    val logginInterceptor = HttpLoggingInterceptor()
    logginInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    this.addNetworkInterceptor(logginInterceptor)

    return this
}

fun OkHttpClient.Builder.addCacheInterceptor(applicationContext:Context, maxStaleDays:Int, cacheSizeMB:Long):OkHttpClient.Builder{

    val cacheSize = (cacheSizeMB*1024*1024)

    this.cache(Cache(applicationContext.cacheDir, cacheSize))

    //adding offline cache interceptor
    this.addInterceptor { chain ->

        var request = chain.request()

        if (!DeviceConnection.isConnected){
            val cacheControl = CacheControl.Builder().maxStale(maxStaleDays, TimeUnit.DAYS).build()

            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
        }


        chain.proceed(request)
    }

    //adding cache network interceptor
    this.addNetworkInterceptor {chain ->

        val response = chain.proceed(chain.request())




        val cacheControl = if (DeviceConnection.isConnected){
            CacheControl.Builder().maxStale(0, TimeUnit.SECONDS).build()
        }else{
            CacheControl.Builder().maxStale(maxStaleDays, TimeUnit.DAYS).build()
        }

        response.newBuilder()
            .removeHeader(HEADER_PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }


    return this
}