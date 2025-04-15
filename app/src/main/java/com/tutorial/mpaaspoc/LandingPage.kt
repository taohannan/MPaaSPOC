package com.tutorial.mpaaspoc


import android.app.Activity
import android.util.Base64
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.alipay.mobile.quinox.bundle.Bundle
import com.mpaas.mriver.api.integration.Mriver
import com.tutorial.mpaaspoc.data.TokenResponse
import org.json.JSONObject


@Composable
fun LandingPage(token: TokenResponse) {
    val context = LocalContext.current
    val activity = context as? Activity

    val decodedPayload = remember(token.accessToken) {
        try {
            decodeJwtPayload(token.accessToken)
        } catch (e: Exception) {
            JSONObject().put("error", "Failed to decode JWT")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("✅ Login Successful!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text("🔐 Access Token Payload:", style = MaterialTheme.typography.titleMedium)

        decodedPayload.keys().forEach { key ->
            val value = decodedPayload.get(key).toString()
            Text("• $key: $value", style = MaterialTheme.typography.bodyMedium)
        }

        // 🔽 Pushes the rest of the content (below) to bottom
        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📦 Launch Mini App", style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = {
                activity?.let {
                    launchMiniApp(it, token.accessToken)
                }
            }) {
                Icon(Icons.Default.PlayArrow, contentDescription = "Start Mini App")
            }
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

fun launchMiniApp(activity: Activity, token: String) {
    activity.let {
        val bundle = android.os.Bundle().apply {
            putString("page","pages/index/index")
            putString("query", "token=$token")
        }

        Mriver.startApp(
            activity,
            "2025041018371492",
            bundle
        )
    }
}
