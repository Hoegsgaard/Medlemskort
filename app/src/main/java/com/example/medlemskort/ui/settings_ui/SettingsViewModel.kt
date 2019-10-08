package com.example.medlemskort.ui.settings_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Indstillinger vil ses her"
    }
    val text: LiveData<String> = _text
}