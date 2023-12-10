package com.stslex.feature.auth.domain

import com.stslex.feature.auth.data.AuthRepository

class AuthInteractorImpl(
    private val authRepository: AuthRepository
) : AuthInteractor {

    override suspend fun auth(username: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun register(username: String, password: String) {
        TODO("Not yet implemented")
    }
}