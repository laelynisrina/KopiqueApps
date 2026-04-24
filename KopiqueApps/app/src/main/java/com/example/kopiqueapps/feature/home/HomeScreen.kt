package com.example.kopiqueapps.feature.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kopiqueapps.core.LocalBackStack
import com.example.kopiqueapps.core.Routes
import com.example.kopiqueapps.feature.MenuItem

@Composable
fun HomeScreen() {
    val backStack = LocalBackStack.current

    val bestSeller = listOf(
        MenuItem("1", "Caramel Latte", "Rp25.000", "⭐ 4.8", "Perpaduan espresso, susu, dan caramel manis."),
        MenuItem("2", "Vanilla Frappe", "Rp28.000", "⭐ 4.7", "Blended vanilla dengan whipped cream lembut.")
    )
    val coffeeMenu = listOf(
        MenuItem("3", "Espresso", "Rp18.000", "⭐ 4.5", "Kopi hitam pekat dengan rasa strong."),
        MenuItem("4", "Americano", "Rp20.000", "⭐ 4.4", "Espresso dengan air panas, ringan dan bold."),
        MenuItem("5", "Cappuccino", "Rp22.000", "⭐ 4.6", "Espresso dengan foam susu yang creamy.")
    )
    val nonCoffeeMenu = listOf(
        MenuItem("6", "Matcha Latte", "Rp24.000", "⭐ 4.6", "Minuman matcha creamy dengan susu."),
        MenuItem("7", "Taro Latte", "Rp24.000", "⭐ 4.5", "Perpaduan taro ungu dengan susu segar."),
        MenuItem("8", "Coklat Panas", "Rp20.000", "⭐ 4.3", "Coklat hangat kaya rasa untuk harimu.")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF5F0EB))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Text("Kopique", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        MenuSection("🏆 Best Seller", bestSeller) { backStack.add(Routes.DetailMenuRoute(id = it)) }
        MenuSection("☕ Coffee", coffeeMenu) { backStack.add(Routes.DetailMenuRoute(id = it)) }
        MenuSection("🍵 Non Coffee", nonCoffeeMenu) { backStack.add(Routes.DetailMenuRoute(id = it)) }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { backStack.removeLastOrNull() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Logout")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun MenuSection(
    title: String,
    items: List<MenuItem>,
    onItemClick: (String) -> Unit
) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4E2C0E),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            MenuCard(item = item, onItemClick = onItemClick)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
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
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(Color(0xFFF0E6D3))
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("☕", fontSize = 40.sp)
            }

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = item.name,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C1A0E),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.price,
                    fontSize = 12.sp,
                    color = Color(0xFF8B5E3C),
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.rating,
                    fontSize = 11.sp,
                    color = Color(0xFF888888)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { onItemClick(item.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("🛒 Order", fontSize = 11.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    OutlinedButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Ayo pakai aplikasi KopiqueApps untuk kemudahan transaksi!"
                                )
                            }
                            context.startActivity(Intent.createChooser(intent, "Share via"))
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF8B5E3C))
                    ) {
                        Text("📩", fontSize = 13.sp)
                    }

                    OutlinedButton(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://maps.app.goo.gl/Y4D7A246Woz8RVEe6")
                            )
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("📍", fontSize = 13.sp)
                    }
                }
            }
        }
    }
}