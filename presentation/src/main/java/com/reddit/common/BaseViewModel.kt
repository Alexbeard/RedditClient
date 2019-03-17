package com.reddit.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reddit.utils.RxDisposable

open class BaseViewModel : ViewModel() {

    var errorMessage = SingleLiveEvent<String>()
    var uploading = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        RxDisposable.unsubscribe(this)
    }
}
