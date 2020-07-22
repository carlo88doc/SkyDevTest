package it.carlo.skydevtest

import android.app.Application
import it.carlo.skydevtest.utils.DeviceConnection

class SkyDevApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
        DeviceConnection.registerNetworkChanges(instance)
    }


    companion object {
        lateinit var instance: SkyDevApplication
            private set
    }
}