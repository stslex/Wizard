package com.stslex.wizard.core.network.api.api.server.http_client

import Wizard.core.network.api.BuildConfig
import com.stslex.wizard.core.network.api.api.server.error_handler.ErrorHandler
import com.stslex.wizard.core.network.api.utils.token.AuthController
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType

class ServerHttpClientImpl(
    private val errorHandler: ErrorHandler,
    private val tokenProvider: AuthController,
    httpClient: HttpClient
) : ServerHttpClient {

    override val client: HttpClient = httpClient.config {
        expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest(errorHandler)
        }
        defaultRequest {
            url(
                scheme = URLProtocol.HTTP.name,
                host = BuildConfig.SERVER_HOST,
                port = BuildConfig.SERVER_PORT.toInt(),
                path = BuildConfig.SERVER_API_VERSION,
                block = {
                    contentType(ContentType.Application.Json)
                }
            )
            headers {
                append(
                    API_KEY_NAME,
                    BuildConfig.SERVER_API_KEY
                )
            }
        }
    }

    override val authClient: HttpClient
        get() = client.config {
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken = tokenProvider.accessToken,
                            refreshToken = tokenProvider.refreshToken
                        )
                    }
                }
            }
        }

    companion object {
        private const val API_KEY_NAME = "X-Api-Key"
    }
}