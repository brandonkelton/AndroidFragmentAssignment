package com.example.androidfragmentassignment.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    val skyColor = MutableLiveData<SkyColor>()
    val planetSize = MutableLiveData<Int>()
    val hasContinents = MutableLiveData<Boolean>()
    val hasSmallContinents = MutableLiveData<Boolean>()
    val hasIslands = MutableLiveData<Boolean>()
    val isPopulated = MutableLiveData<Boolean>()
}
