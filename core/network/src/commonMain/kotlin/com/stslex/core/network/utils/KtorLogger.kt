package com.stslex.core.network.utils

import io.ktor.client.plugins.logging.Logger
import com.stslex.core.core.Logger as Log

object KtorLogger : Logger {

    private const val TAG = "KTOR_LOGGER"

    override fun log(message: String) {
        Log.debug(message, TAG)
    }
}