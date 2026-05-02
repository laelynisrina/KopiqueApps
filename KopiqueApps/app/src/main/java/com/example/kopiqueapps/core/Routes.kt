package com.example.kopiqueapps.core

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes : NavKey {

    @Serializable
    data object AuthRoute : Routes()

    @Serializable
    data object LoginRoute : Routes()

    @Serializable
    data object HomeRoute : Routes()

    @Serializable
    data object AddCoffeeRoute : Routes()

    @Serializable
    data class DetailMenuRoute(val id: String) : Routes()
}