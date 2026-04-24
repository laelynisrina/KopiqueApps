package com.example.kopiqueapps.core

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
object Routes {
    @Serializable
    data object AuthRoute : NavKey

    @Serializable
    data object ListMenuRoute : NavKey

    @Serializable
    data object CreateMenuRoute : NavKey

    @Serializable
    data class DetailMenuRoute(
        val id: String
    ) : NavKey
}