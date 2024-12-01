package com.stslex.wizard.core.network.api.clients.auth.client

import com.stslex.wizard.core.network.api.clients.auth.response.LoginOkResponse

interface AuthClient {

    suspend fun authUser(login: String, password: String): LoginOkResponse

    suspend fun registerUser(login: String, username: String, password: String): LoginOkResponse
}
