package com.stslex.core.ui.base.image

import io.ktor.client.plugins.logging.Logger

object KamelLogger : Logger {

    private const val TAG = "KAMEL"

    override fun log(message: String) {
        com.stslex.core.core.Logger.debug(message, TAG)
    }
}