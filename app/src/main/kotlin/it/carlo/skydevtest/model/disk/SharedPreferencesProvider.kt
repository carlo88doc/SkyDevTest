package it.carlo.skydevtest.model.disk

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Single
import it.carlo.skydevtest.SkyDevApplication

class SharedPreferencesProvider {

    companion object{

        private const val KEY_SHARED:String = "SharedPreferencesProvider"

        private const val KEY_OVER_18 = ".key_over18"

        private val preferences: SharedPreferences = SkyDevApplication.instance.getSharedPreferences(KEY_SHARED, Context.MODE_PRIVATE)

        private fun getBoolean(key:String, defaultValue:Boolean):Single<Boolean>{
            return Single.create {
                preferences.getBoolean(key, defaultValue)
                it.onSuccess(true)
            }
        }

        private fun saveBoolean(key:String, value:Boolean):Single<Boolean>{
            return Single.create {
                preferences.edit().putBoolean(key, value).apply()
                it.onSuccess(true)
            }
        }

        private fun getString(key:String, defaultValue:String):Single<String>{
            return Single.create<String> {
                val result = preferences.getString(key, defaultValue) ?: defaultValue
                it.onSuccess(result)
            }
        }

        private fun saveString(key:String, value:String):Single<Boolean>{
            return Single.create {
                preferences.edit().putString(key, value).apply()
                it.onSuccess(true)
            }
        }

        private fun remove(key:String):Single<Boolean>{
            return Single.create {
                preferences.edit().remove(key).apply()
                it.onSuccess(true)
            }
        }

        fun setBlockOver18ContentsSingle(over18:Boolean):Single<Boolean>{
            return saveBoolean(KEY_OVER_18, over18)
        }

        fun getBlockOver18ContentsSingle():Single<Boolean>{
            return getBoolean(KEY_OVER_18, false)
        }

    }
}