package com.tutorial.mpaaspoc

import android.util.Base64
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tutorial.mpaaspoc.data.TokenResponse
import org.json.JSONObject


@Composable
fun LandingPage(token: TokenResponse) {
    val decodedPayload = remember(token.accessToken) {
        try {
            decodeJwtPayload(token.accessToken)
        } catch (e: Exception) {
            JSONObject().put("error", "Failed to decode JWT")
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("✅ Login Successful!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text("🔐 Access Token Payload:", style = MaterialTheme.typography.titleMedium)

        decodedPayload.keys().forEach { key ->
            val value = decodedPayload.get(key).toString()
            Text("• $key: $value", style = MaterialTheme.typography.bodyMedium)
        }
    }


}

fun decodeJwtPayload(jwt: String): JSONObject {
    val parts = jwt.split(".")
    if (parts.size < 2) throw IllegalArgumentException("Invalid JWT")

    val payload = parts[1]
    val decodedBytes = Base64.decode(payload, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
    val decodedJson = String(decodedBytes, Charsets.UTF_8)
    return JSONObject(decodedJson)
}
