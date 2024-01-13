package com.stslex.core.network.api.server

import Wizard.core.network.BuildConfig
import com.stslex.core.core.AppDispatcher
import com.stslex.core.network.api.base.NetworkClient
import com.stslex.core.network.api.base.NetworkClientBuilder.setupLogging
import com.stslex.core.network.api.base.NetworkClientBuilder.setupNegotiation
import com.stslex.core.network.utils.token.AuthController
import com.stslex.core.network.utils.token.toModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface ServerApiClient : NetworkClient

class ServerApiClientImpl(
    private val tokenProvider: AuthController,
    private val appDispatcher: AppDispatcher
) : ServerApiClient {

    private var refreshJob: Job? = null

    val client = HttpClient(CIO) {
        setupNegotiation()
        setupLogging()
        expectSuccess = true
        HttpResponseValidator {
//            handleResponseExceptionWithRequest(errorParser)
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
                append(API_KEY_NAME, BuildConfig.SERVER_API_KEY)
            }
        }
    }

    private val authClient: HttpClient
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

    override suspend fun <T> request(
        request: suspend HttpClient.() -> T
    ): T = withContext(appDispatcher.io) {
        request(authClient)

//        try {
//            request(authClient)
//        } catch (error: ErrorRepeat) {
//            request(authClient)
////            request(request) // TODO
//        }
    }

    private val errorParser: suspend (Throwable, HttpRequest) -> Unit
        get() = { exception, _ ->
            val clientException = exception as? ResponseException ?: throw exception
            if (HttpStatusCode.Unauthorized.value == clientException.response.status.value) {
                refreshToken()
            } else {
                throw clientException
            }
        }

    private suspend fun refreshToken() {
        if (refreshJob?.isActive == true) return
        refreshJob = coroutineScope {
            launch {
                val tokenResponse = client
                    .get("passport/refresh") {
                        bearerAuth(tokenProvider.refreshToken)
                    }
                    .body<TokenResponseModel>()
                tokenProvider.update(tokenResponse.toModel())
                throw ErrorRepeat
            }
        }
    }

    companion object {
        private const val API_KEY_NAME = "X-Api-Key"
    }
}

data object ErrorRepeat : Throwable()

data object ErrorRefresh : Throwable()
