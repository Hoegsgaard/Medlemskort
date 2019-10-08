package com.example.medlemskort.ui.card_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Her vil dine medlemskort blive vist"
    }
    val text: LiveData<String> = _text
}