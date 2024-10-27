package com.stslex.wizard.core.network.clients.auth.client

import com.stslex.core.network.api.server.client.ServerApiClient
import com.stslex.core.network.clients.auth.request.LoginRequest
import com.stslex.wizard.core.network.clients.auth.request.RegisterRequest
import com.stslex.core.network.clients.auth.response.LoginOkResponse
import com.stslex.core.network.utils.token.AuthController
import com.stslex.core.network.utils.token.toModel
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthClientImpl(
    private val networkClient: ServerApiClient,
    private val tokenController: AuthController
) : AuthClient {

    override suspend fun authUser(
        login: String,
        password: String
    ): LoginOkResponse = networkClient.request {
        post("$AUTH_URL/login") {
            setBody(
                LoginRequest(
                    login = login,
                    password = password
                )
            )
        }.body<LoginOkResponse>().also {
            tokenController.update(it.toModel())
        }
    }

    override suspend fun registerUser(
        login: String,
        username: String,
        password: String
    ): LoginOkResponse = networkClient.request {
        post("$AUTH_URL/registration") {
            setBody(
                RegisterRequest(
                    login = login,
                    password = password,
                    username = username
                )
            )
        }.body<LoginOkResponse>().also {
            tokenController.update(it.toModel())
        }
    }

    companion object {
        private const val AUTH_URL = "passport"
    }
}