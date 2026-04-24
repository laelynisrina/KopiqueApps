package com.example.kopiqueapps.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.kopiqueapps.feature.auth.LoginScreen
import com.example.kopiqueapps.feature.detail.DetailScreen
import com.example.kopiqueapps.feature.home.HomeScreen

@Composable
fun ComposeApp() {
    val backStack = rememberNavBackStack(Routes.AuthRoute)

    MaterialTheme {
        CompositionLocalProvider(LocalBackStack provides backStack) {
            NavDisplay(
                backStack = backStack,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {
                    entry<Routes.AuthRoute> { LoginScreen() }
                    entry<Routes.ListMenuRoute> { HomeScreen() }
                    entry<Routes.DetailMenuRoute> { route ->
                        DetailScreen(name = route.id)
                    }
                }
            )
        }
    }
}