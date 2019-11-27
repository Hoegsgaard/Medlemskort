package com.example.medlemskort

data class Card
    (
    var cardname: String = "",
    var cardnumber: Long = -1,
    var brand: String = "",
    var frontImage: String = "",
    var backImage: String = "",
    var note: String = ""
)
