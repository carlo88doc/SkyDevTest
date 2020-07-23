package it.carlo.skydevtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import it.carlo.skydevtest.utils.SingleEvent

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var progressLiveData = MutableLiveData<SingleEvent<Boolean>>()

}