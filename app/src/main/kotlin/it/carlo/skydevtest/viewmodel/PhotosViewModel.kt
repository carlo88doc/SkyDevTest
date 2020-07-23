package it.carlo.skydevtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import it.carlo.skydevtest.model.PhotoRepository
import it.carlo.skydevtest.model.data.RedditPhotoModelView
import it.carlo.skydevtest.utils.ERROR_RESOURCE_EMPTY
import it.carlo.skydevtest.utils.SingleEvent
import it.carlo.skydevtest.utils.logError

class PhotosViewModel(application: Application) : AndroidViewModel(application) {

    var photosLiveData = MutableLiveData<ArrayList<RedditPhotoModelView>> ()
    var errorLiveData = MutableLiveData<Int>()

    private val disposables = CompositeDisposable()

    fun searchRedditPhotos(progressLiveData:MutableLiveData<SingleEvent<Boolean>>, placeholder:String){
        progressLiveData.postValue(SingleEvent(true))

        disposables.add(
            PhotoRepository.getPhotosSingle(placeholder)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    photosLiveData.postValue(it)
                    progressLiveData.postValue(SingleEvent(false))

                },{
                    it.logError()
                    errorLiveData.postValue(ERROR_RESOURCE_EMPTY)
                    progressLiveData.postValue(SingleEvent(false))

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}