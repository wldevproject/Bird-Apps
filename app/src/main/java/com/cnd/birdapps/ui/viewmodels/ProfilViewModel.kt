package com.cnd.birdapps.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class ProfilViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _loveBird = MutableLiveData<String>().apply {

    }

    val loveBird: LiveData<String> = _loveBird
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}