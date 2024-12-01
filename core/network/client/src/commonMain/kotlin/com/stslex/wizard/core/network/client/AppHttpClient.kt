package com.stslex.wizard.core.network.client

import com.stslex.wizard.core.network.client.NetworkClientBuilder.setupLogging
import com.stslex.wizard.core.network.client.NetworkClientBuilder.setupNegotiation
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

internal object AppHttpClient {

    val HttpClient: HttpClient = HttpClient(CIO) {
        setupNegotiation()
        setupLogging()
    }
}