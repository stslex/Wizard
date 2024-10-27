package com.stslex.wizard.feature.auth.data

interface AuthRepository {

    suspend fun auth(login: String, password: String)

    suspend fun register(login: String, username: String, password: String)
}
