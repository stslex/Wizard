package com.stslex.wizard.core.network.api.server.model

/**
 * Error repeat request.
 * Show that the request was repeated after a refresh token
 * @see ServerApiClientImpl.request
 */
internal data object ErrorRepeatEnd : Throwable()