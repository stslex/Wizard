package com.stslex.core.network.utils.token

import com.stslex.core.database.store.UserStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthControllerImpl(
    private val userStore: UserStore
) : AuthController {

    init {
        userStore.accessToken = "123"
    }

    override val isAuth: Boolean
        get() = userStore.accessToken.isNotEmpty()

    private val _isAuthFlow: MutableStateFlow<Boolean> = MutableStateFlow(isAuth)
    override val isAuthFlow: StateFlow<Boolean> = _isAuthFlow.asStateFlow()

    override val accessToken: String
        get() = userStore.accessToken

    override val refreshToken: String
        get() = userStore.refreshToken

    override suspend fun update(token: TokenModel) {
        userStore.accessToken = token.accessToken
        userStore.refreshToken = token.refreshToken
        userStore.username = token.username
        userStore.uuid = token.uuid
        _isAuthFlow.emit(token.accessToken.isNotEmpty())
    }

    override suspend fun logOut() {
        userStore.clear()
        _isAuthFlow.emit(false)
    }
}