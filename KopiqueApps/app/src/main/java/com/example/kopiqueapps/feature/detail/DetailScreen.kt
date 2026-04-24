package com.example.kopiqueapps.feature.detail

import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kopiqueapps.core.LocalBackStack

@Composable
fun DetailScreen(name: String) {

    val backStack = LocalBackStack.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Menu: $name")

        Button(onClick = {
            backStack.removeLastOrNull()
        }) {
            Text("Back")
        }
    }
}