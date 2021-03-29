package com.cnd.birdapps.ui.view.sigin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SigInViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Sign-In Fragment"
    }
    val text: LiveData<String> = _text
}