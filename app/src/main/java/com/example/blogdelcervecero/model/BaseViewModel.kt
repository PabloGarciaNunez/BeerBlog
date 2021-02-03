package com.example.blogdelcervecero.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val dataLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
}