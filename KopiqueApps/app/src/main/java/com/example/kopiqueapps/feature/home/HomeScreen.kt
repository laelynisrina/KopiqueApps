package com.example.kopiqueapps.feature.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kopiqueapps.core.LocalBackStack
import com.example.kopiqueapps.core.Routes
import com.example.kopiqueapps.feature.MenuItem

@Composable
fun HomeScreen() {

    val backStack = LocalBackStack.current

    val bestSeller = listOf(
        MenuItem("1", "Caramel Latte", "Rp25.000", "⭐ 4.8", ""),
        MenuItem("2", "Vanilla Frappe", "Rp28.000", "⭐ 4.7", "")
    )

    val coffeeMenu = listOf(
        MenuItem("3", "Espresso", "Rp18.000", "⭐ 4.5", ""),
        MenuItem("4", "Americano", "Rp20.000", "⭐ 4.4", ""),
        MenuItem("5", "Cappuccino", "Rp22.000", "⭐ 4.6", "")
    )

    val nonCoffeeMenu = listOf(
        MenuItem("6", "Matcha Latte", "Rp24.000", "⭐ 4.6", ""),
        MenuItem("7", "Taro Latte", "Rp24.000", "⭐ 4.5", ""),
        MenuItem("8", "Coklat Panas", "Rp20.000", "⭐ 4.3", "")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F0EB))
    ) {

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "Kopique",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        item {
            MenuSection("🏆 Best Seller", bestSeller) {
                backStack.add(Routes.DetailMenuRoute(id = it))
            }
        }

        item {
            MenuSection("☕ Coffee", coffeeMenu) {
                backStack.add(Routes.DetailMenuRoute(id = it))
            }
        }

        item {
            MenuSection("🍵 Non Coffee", nonCoffeeMenu) {
                backStack.add(Routes.DetailMenuRoute(id = it))
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Button(
                onClick = {
                    backStack.add(Routes.AddCoffeeRoute)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Tambah Coffee")
            }
        }

        item {
            TextButton(
                onClick = { backStack.removeLastOrNull() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Logout")
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MenuSection(
    title: String,
    items: List<MenuItem>,
    onItemClick: (String) -> Unit
) {
    Column {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E2C0E),
            modifier = Modifier.padding(16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { item ->
                MenuCard(item, onItemClick)
            }
        }
    }
}

@Composable
fun MenuCard(
    item: MenuItem,
    onItemClick: (String) -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable { onItemClick(item.id) },
        shape = RoundedCornerShape(16.dp)
    ) {

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(Color(0xFFF0E6D3)),
                contentAlignment = Alignment.Center
            ) {
                Text("☕", fontSize = 40.sp)
            }

            Column(modifier = Modifier.padding(10.dp)) {

                Text(item.name, fontWeight = FontWeight.Bold)

                Text(item.price)

                Text(item.rating)

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { onItemClick(item.id) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Order")
                }

                Row {
                    OutlinedButton(onClick = {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, "Download KopiqueApps!")
                        }
                        context.startActivity(Intent.createChooser(intent, "Share"))
                    }) {
                        Text("📩")
                    }

                    OutlinedButton(onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://maps.app.goo.gl/")
                        )
                        context.startActivity(intent)
                    }) {
                        Text("📍")
                    }
                }
            }
        }
    }
}