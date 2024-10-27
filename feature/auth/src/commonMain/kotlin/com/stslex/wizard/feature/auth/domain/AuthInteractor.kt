package com.stslex.wizard.feature.auth.domain

interface AuthInteractor {

    suspend fun auth(login: String, password: String)

    suspend fun register(login: String, username: String, password: String)
}
