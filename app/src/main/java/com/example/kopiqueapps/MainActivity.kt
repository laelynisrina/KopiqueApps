package com.example.kopiqueapps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi Tombol
        val btnWA = findViewById<Button>(R.id.btnWA)
        val btnMaps = findViewById<Button>(R.id.btnMaps)
        val btnShare = findViewById<Button>(R.id.btnShare)
        val btnOrder = findViewById<Button>(R.id.btnOrder)

        // 2. Klik WhatsApp
        btnWA.setOnClickListener {
            val nomorWA = "62882007844390" // Gunakan format kode negara (62)
            val pesan = "Halo admin KopiqueApps, saya ingin bertanya."
            val url = "https://wa.me/62882007844390"
            startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) })
        }

        // 3. Klik Google Maps
        btnMaps.setOnClickListener {
            val lat = "-6.175392" // Contoh koordinat (Monas)
            val lon = "106.827153"
            val label = "Lokasi Kopique"
            val uri = Uri.parse("geo:$lat,$lon?q=$lat,$lon($label)")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        // 4. Klik Share
        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Ayo pakai aplikasi KopiqueApps untuk kemudahan transaksi!"
                )
            }
            startActivity(Intent.createChooser(shareIntent, "Bagikan ke:"))
        }
        // 5. Logika Klik Order (Tombol tambahan)
        btnOrder.setOnClickListener {
            val uri = Uri.parse("https://wa.me/62882007844390")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}