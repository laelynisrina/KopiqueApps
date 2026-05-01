package com.example.kopiqueapps.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kopiqueapps.core.LocalBackStack
import com.example.kopiqueapps.core.Routes

@Composable
fun LoginScreen() {
    val backStack = LocalBackStack.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LoginContent(
        username = username,
        password = password,
        onUsernameChange = { username = it },
        onPasswordChange = { password = it },
        onLoginClick = {
            if (username.isNotEmpty() && password.isNotEmpty()) {
                backStack.add(Routes.HomeRoute)            }
        }
    )
}

@Composable
fun LoginContent(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Kopique Cofee Shop",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = username,
            onValueChange = onUsernameChange,
            placeholder = { Text("Masukkan Username") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = { Text("Masukkan Password") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()) {
            Text("Login")
        }
    }
}

@Preview
@Composable
private fun LoginPreview() {
    LoginContent(
        username = "budi",
        password = "password123",
        onUsernameChange = {},
        onPasswordChange = {},
        onLoginClick = {}
    )
}