package it.carlo.skydevtest.model

import io.reactivex.Single
import it.carlo.skydevtest.model.disk.SharedPreferencesProvider

class DiskRepository {

    companion object{

        fun getOver18Single():Single<Boolean>{
            return SharedPreferencesProvider.getBlockOver18ContentsSingle()
        }

        fun setBlockOver18Single(blockOver18:Boolean):Single<Boolean>{
            return SharedPreferencesProvider.setBlockOver18ContentsSingle(blockOver18)
        }
    }
}