package com.tutorial.mpaaspoc

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.tutorial.mpaaspoc.data.TokenResponse

@Composable
fun AutoLoginApp() {
    var tokenResponse by remember { mutableStateOf<TokenResponse?>(null) }

    LaunchedEffect(true) {
        val service = RetrofitInstance.api
        try {
            tokenResponse = service.login(
                grantType = "password",
                clientId = "kelichap-client",
                username = "john.carter",
                password = "abc123"
            )
        } catch (e: Exception) {
            Log.e("Login", "Error: ${e.message}")
        }
    }

    if (tokenResponse != null) {
        LandingPage(tokenResponse!!)
    } else {
        Text("Logging in...")
    }
}
