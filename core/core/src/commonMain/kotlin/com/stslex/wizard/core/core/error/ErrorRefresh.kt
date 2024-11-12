package com.stslex.wizard.core.core.error

/**
 * Error refresh token response
 * @see ServerApiClientImpl.refreshToken
 * @see ServerApiClientImpl.errorParser
 * @see ServerApiClientImpl.request
 */
data object ErrorRefresh : Throwable()