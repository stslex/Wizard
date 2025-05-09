package com.stslex.wizard.core.network.api.utils.token

import kotlinx.coroutines.flow.StateFlow

interface AuthController {

    val isAuth: Boolean
    val isAuthFlow: StateFlow<Boolean>
    val accessToken: String
    val refreshToken: String

    suspend fun update(token: TokenModel)
    suspend fun logout()
}
