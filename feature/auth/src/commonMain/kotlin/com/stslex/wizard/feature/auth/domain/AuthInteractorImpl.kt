package com.stslex.wizard.feature.auth.domain

import com.stslex.wizard.feature.auth.data.AuthRepository
import com.stslex.wizard.feature.auth.di.AuthScope
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(AuthScope::class)
@Scoped
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