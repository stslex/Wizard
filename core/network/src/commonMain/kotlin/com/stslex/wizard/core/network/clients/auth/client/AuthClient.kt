package com.stslex.wizard.core.network.clients.auth.client

import com.stslex.core.network.clients.auth.response.LoginOkResponse

interface AuthClient {

    suspend fun authUser(login: String, password: String): LoginOkResponse

    suspend fun registerUser(login: String, username: String, password: String): LoginOkResponse
}

