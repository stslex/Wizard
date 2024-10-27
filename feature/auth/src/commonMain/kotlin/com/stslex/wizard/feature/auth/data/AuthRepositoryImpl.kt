package com.stslex.wizard.feature.auth.data

import com.stslex.wizard.core.network.clients.auth.client.AuthClient

class AuthRepositoryImpl(
    private val client: AuthClient
) : AuthRepository {

    override suspend fun auth(login: String, password: String) {
        client.authUser(login, password)
    }

    override suspend fun register(login: String, username: String, password: String) {
        client.registerUser(login, username, password)
    }
}