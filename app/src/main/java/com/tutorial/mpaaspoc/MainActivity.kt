package com.tutorial.mpaaspoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutorial.mpaaspoc.data.TokenResponse
import com.tutorial.mpaaspoc.ui.theme.MPaaSPOCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MPaaSPOCTheme {
                var tokenResponse by remember { mutableStateOf<TokenResponse?>(null) }
                var errorMessage by remember { mutableStateOf<String?>(null) }
                var isLoading by remember { mutableStateOf(true) }

                LaunchedEffect(true) {
                    try {
                        val response = RetrofitInstance.api.login(
                            grantType = "password",
                            clientId = "kelichap-client",
                            username = "john.carter",
                            password = "abc123"
                        )
                        tokenResponse = response
                    } catch (e: Exception) {
                        errorMessage = e.localizedMessage ?: "Login failed"
                    } finally {
                        isLoading = false
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                        when {
                            isLoading -> {
                                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                            }
                            errorMessage != null -> {
                                Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
                            }
                            tokenResponse != null -> {
                                LandingPage(tokenResponse!!)
                            }
                        }
                    }
                }
            }
        }
    }
}
