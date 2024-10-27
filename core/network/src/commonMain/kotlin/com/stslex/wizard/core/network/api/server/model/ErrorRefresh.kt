package com.stslex.wizard.core.network.api.server.model

/**
 * Error refresh token response
 * @see ServerApiClientImpl.refreshToken
 * @see ServerApiClientImpl.errorParser
 * @see ServerApiClientImpl.request
 */
data object ErrorRefresh : Throwable()