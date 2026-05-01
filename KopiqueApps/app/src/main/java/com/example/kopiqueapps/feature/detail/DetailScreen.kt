package com.example.kopiqueapps.feature.detail

import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kopiqueapps.core.LocalBackStack

@Composable
fun DetailScreen(name: String) {

    val backStack = LocalBackStack.current
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Coffee") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("Menu: $name")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    backStack.removeLastOrNull()
                }
            ) {
                Text("Back")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    showDialog = true
                }
            ) {
                Text("Hapus")
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                            backStack.removeLastOrNull()
                        }
                    ) {
                        Text("Hapus")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text("Batal")
                    }
                },
                title = { Text("Konfirmasi") },
                text = { Text("Yakin hapus coffee ini?") }
            )
        }
    }
}