package it.carlo.skydevtest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class DeviceConnection {

    enum class DeviceConnectionStatus{
        AVAILABLE, UNAVAILABLE, LOST, LOSING,
    }

    companion object{

        var status:DeviceConnectionStatus = DeviceConnectionStatus.LOST

        var isConnected = status==DeviceConnectionStatus.AVAILABLE

        private var connectivityManager:ConnectivityManager? = null

        private var networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onLost(network: Network?) {
                status = DeviceConnectionStatus.LOST
            }override fun onUnavailable() {
                status = DeviceConnectionStatus.UNAVAILABLE
            }override fun onLosing(network: Network?, maxMsToLive: Int) {
                status = DeviceConnectionStatus.LOSING
            }override fun onAvailable(network: Network?) {
                status = DeviceConnectionStatus.AVAILABLE
            }
        }

        fun registerNetworkChanges(context:Context){
            connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkRequest = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
                .build()

            connectivityManager!!.registerNetworkCallback(networkRequest, networkCallback)

        }

        fun unRegisterNetworkChanges(){
            connectivityManager?.unregisterNetworkCallback(networkCallback)
        }
    }
}