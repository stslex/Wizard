package com.stslex.core.ui.base.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json

@OptIn(InternalAPI::class)
@Composable
fun NativeNetworkImage(
    url: String,
    contentDescription: String?,
    contentScale: ContentScale,
    modifier: Modifier = Modifier,
) {

}

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json { isLenient = true; ignoreUnknownKeys = true })
    }
}