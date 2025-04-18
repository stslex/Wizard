package com.stslex.wizard.core.network.api.api.kinopoisk.api

import Wizard.core.network.api.BuildConfig
import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.network.api.api.base.BaseNetworkClient
import com.stslex.wizard.core.network.client.DefaultRequest
import io.ktor.client.HttpClient

class KinopoiskApiClientImpl(
    appDispatcher: AppDispatcher,
    client: HttpClient
) : KinopoiskApiClient, BaseNetworkClient(
    appDispatcher = appDispatcher,
    client = client,
    defaultRequest = DEFAULT_REQUEST
) {

    companion object {
        // TODO parse error
        private const val KINOPOISK_HOST_URL = "kinopoiskapiunofficial.tech/api/v2.2"
        private const val API_KEY = BuildConfig.KINOPOISK_API_KEY
        private const val API_HEADER = "X-API-KEY"
        private const val CONTENT_TYPE = "Content-Type"
        private const val CONTENT_TYPE_VALUE = "application/json"

        private val DEFAULT_REQUEST =
            DefaultRequest(
                hostUrl = KINOPOISK_HOST_URL,
                headers = mapOf(
                    API_HEADER to API_KEY,
                    CONTENT_TYPE to CONTENT_TYPE_VALUE
                )
            )
    }
}