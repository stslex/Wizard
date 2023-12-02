package com.stslex.core.network.api.kinopoisk

import Wizard.core.network.BuildConfig
import com.stslex.core.core.AppDispatcher
import com.stslex.core.network.api.base.BaseNetworkClient
import com.stslex.core.network.api.model.DefaultRequest

class KinopoiskApiClientImpl(
    appDispatcher: AppDispatcher
) : KinopoiskApiClient, BaseNetworkClient(
    appDispatcher = appDispatcher,
    defaultRequest = DEFAULT_REQUEST
) {

    companion object {
        private const val KINOPOISK_HOST_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/"
        private const val API_KEY = BuildConfig.KINOPOISK_API_KEY
        private const val API_HEADER = "X-API-KEY"
        private const val CONTENT_TYPE = "Content-Type"
        private const val CONTENT_TYPE_VALUE = "application/json"

        private val DEFAULT_REQUEST = DefaultRequest(
            hostUrl = KINOPOISK_HOST_URL,
            headers = mapOf(
                API_HEADER to API_KEY,
                CONTENT_TYPE to CONTENT_TYPE_VALUE
            )
        )
    }
}