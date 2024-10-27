package com.stslex.wizard.feature.auth.domain

import com.stslex.wizard.feature.auth.data.AuthRepository

class AuthInteractorImpl(
    private val authRepository: AuthRepository
) : AuthInteractor {

    override suspend fun auth(login: String, password: String) {
        authRepository.auth(
            login = login,
            password = password
        )
    }

    override suspend fun register(login: String, username: String, password: String) {
        authRepository.register(
            login = login,
            username = username,
            password = password
        )
    }
}