package com.stslex.wizard.feature.auth.data

import com.stslex.wizard.core.network.api.clients.auth.client.AuthClient
import com.stslex.wizard.feature.auth.di.AuthScope
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(AuthScope::class)
@Scoped
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