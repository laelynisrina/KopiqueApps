package com.example.kopiqueapps.feature.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kopiqueapps.core.LocalBackStack
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCoffeeScreen() {
    Text("Add Coffee Screen")

    val backStack = LocalBackStack.current
    val scope = rememberCoroutineScope()

    var coffeeName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Belum dipilih") }

    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    var coffeeList by remember {
        mutableStateOf(listOf("Espresso", "Latte", "Cappuccino"))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Coffee") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            backStack.removeLastOrNull()
                        }
                    ) {
                        Text("<")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = coffeeName,
                onValueChange = { coffeeName = it },
                label = { Text("Nama Coffee") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { showSheet = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kategori: $selectedCategory")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (coffeeName.isNotEmpty()) {
                        coffeeList = coffeeList + coffeeName
                        coffeeName = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tambah ke List")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Daftar Coffee:")

            // LAZY COLUMN DI SINI
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                items(coffeeList) { coffee ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = coffee,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }

    // BOTTOM SHEET
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showSheet = false
            },
            sheetState = sheetState
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("Pilih Kategori")

                Spacer(modifier = Modifier.height(12.dp))

                listOf("Hot", "Cold", "Manual Brew").forEach { category ->

                    Text(
                        text = category,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .clickable {
                                scope.launch {
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    selectedCategory = category
                                    showSheet = false
                                }
                            }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            showSheet = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Batal")
                }
            }
        }
    }
}