package com.stslex.feature.auth.domain

interface AuthInteractor {

    suspend fun register(username: String, password: String)

    suspend fun auth(username: String, password: String)
}
