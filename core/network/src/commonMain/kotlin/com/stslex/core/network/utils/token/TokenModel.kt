package com.stslex.core.network.utils.token

import com.stslex.core.network.api.server.model.TokenResponseModel
import com.stslex.core.network.clients.auth.response.LoginOkResponse

data class TokenModel(
    val uuid: String,
    val username: String,
    val accessToken: String,
    val refreshToken: String
)

fun TokenResponseModel.toModel() = TokenModel(
    uuid = uuid,
    username = username,
    accessToken = accessToken,
    refreshToken = refreshToken
)

fun LoginOkResponse.toModel() = TokenModel(
    uuid = uuid,
    username = username,
    accessToken = accessToken,
    refreshToken = refreshToken
)
