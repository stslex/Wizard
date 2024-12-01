package com.stslex.wizard.core.network.api.api.server.error_handler

import com.stslex.wizard.core.network.api.api.server.error_handler.RefreshTokenValidator.setupResponseValidator
import com.stslex.wizard.core.network.api.api.server.http_client.ServerHttpClient
import com.stslex.wizard.core.network.api.api.server.model.ErrorRepeatEnd
import com.stslex.wizard.core.network.api.api.server.model.TokenResponseModel
import com.stslex.wizard.core.network.api.utils.token.AuthController
import com.stslex.wizard.core.network.api.utils.token.toModel
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ErrorHandlerImpl(
    private val client: Lazy<ServerHttpClient>,
    private val tokenProvider: AuthController,
) : ErrorHandler {

    private var refreshJob: Job? = null

    override suspend fun invoke(cause: Throwable, request: HttpRequest) {
        when {
            cause !is ResponseException -> throw cause
            cause.response.status.value == HttpStatusCode.Unauthorized.value -> refreshToken()
            else -> throw cause
        }
    }

    private suspend fun refreshToken() {
        if (refreshJob?.isActive == true) return
        refreshJob = coroutineScope {
            launch {
                val tokenResponse = client
                    .value
                    .client
                    .setupResponseValidator()
                    .get("passport/refresh") {
                        bearerAuth(tokenProvider.refreshToken)
                    }
                    .body<TokenResponseModel>()
                tokenProvider.update(tokenResponse.toModel())
                // TODO remove error throw after refresh token (move logic out from throwing)
                throw ErrorRepeatEnd
            }
        }
    }
}