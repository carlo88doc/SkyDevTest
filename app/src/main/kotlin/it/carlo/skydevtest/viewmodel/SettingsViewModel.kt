package it.carlo.skydevtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import it.carlo.skydevtest.model.DiskRepository
import it.carlo.skydevtest.utils.SingleEvent
import it.carlo.skydevtest.utils.logError

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private var over18LiveData:MutableLiveData<Boolean>? = null

    private val disposables = CompositeDisposable()

    fun getOver18LiveData(progressLiveData:MutableLiveData<SingleEvent<Boolean>>):MutableLiveData<Boolean>{
        if (over18LiveData==null){
            progressLiveData.postValue(SingleEvent(true))
            over18LiveData = MutableLiveData()
            disposables.add(
                DiskRepository.getOver18Single()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        over18LiveData!!.postValue(it)
                        progressLiveData.postValue(SingleEvent(false))
                    },{
                        it.logError()
                        over18LiveData!!.postValue(false)
                        progressLiveData.postValue(SingleEvent(false))
                    })
            )
        }

        return over18LiveData!!
    }

    fun blockOver18Contents(progressLiveData:MutableLiveData<SingleEvent<Boolean>>, blockOver18:Boolean){
        progressLiveData.postValue(SingleEvent(true))

        disposables.add(
            DiskRepository.setBlockOver18Single(blockOver18)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    progressLiveData.postValue(SingleEvent(false))
                },{
                    it.logError()
                    progressLiveData.postValue(SingleEvent(false))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposables.clear()
    }

}