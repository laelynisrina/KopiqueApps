package com.example.kopiqueapps.feature

data class MenuItem(
    val id: String,
    val name: String,
    val price: String,
    val rating: String,
    val description: String,
    val imageUrl: String = ""
)