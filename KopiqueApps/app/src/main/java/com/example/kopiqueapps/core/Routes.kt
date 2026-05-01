package com.example.kopiqueapps.core

import androidx.navigation3.runtime.NavKey

sealed class Routes : NavKey {

    data object AuthRoute : Routes()

    data object LoginRoute : Routes()

    data object HomeRoute : Routes()

    data object AddCoffeeRoute : Routes()

    data class DetailMenuRoute(val id: String) : Routes()
}