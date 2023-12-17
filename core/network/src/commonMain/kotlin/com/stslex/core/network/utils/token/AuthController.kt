package com.stslex.core.network.utils.token

import kotlinx.coroutines.flow.StateFlow

interface AuthController {
    val isAuth: Boolean
    val isAuthFlow: StateFlow<Boolean>
    val accessToken: String
    val refreshToken: String

    fun update(token: TokenModel)
}

