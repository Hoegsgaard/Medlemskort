package com.example.medlemskort

data class Card
    (
    //TODO Add a "Brand" field to the Card class and the places where we use a Brand we should add it to the card so we can reuse the brand in multiple fragments.
    //TODO Add two String fields that contain a reference to the front and back image of a card
    //TODO Add a String note that is the note given to a card.
    val cardname: String = "",
    val cardnumber: Long = -1
)
