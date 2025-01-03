package com.stslex.wizard.core.core

import co.touchlab.kermit.Logger as Log

object Logger {

    private const val DEFAULT_TAG = "WIZARD"

    fun e(
        throwable: Throwable,
        tag: String? = null,
        message: String? = null
    ) {
        // TODO check build config if (BuildConfig.DEBUG.not()) return
        Log.e(
            tag = tag ?: DEFAULT_TAG,
            throwable = throwable,
            messageString = message ?: throwable.message.orEmpty(),
        )
    }

    fun d(
        message: String,
        tag: String? = null,
    ) {
        // TODO check build config if (BuildConfig.DEBUG.not()) return
        Log.d(
            tag = tag ?: DEFAULT_TAG,
            messageString = message
        )
    }
}