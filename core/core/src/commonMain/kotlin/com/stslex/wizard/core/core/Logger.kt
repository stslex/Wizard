package com.stslex.wizard.core.core

import co.touchlab.kermit.Logger as Log

object Logger {

    internal const val DEFAULT_TAG = "WIZARD"

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

    fun i(
        message: String,
        tag: String? = null,
    ) {
        // TODO check build config if (BuildConfig.DEBUG.not()) return
        Log.i(
            tag = tag ?: DEFAULT_TAG,
            messageString = message
        )
    }

    fun v(
        message: String,
        tag: String? = null,
    ) {
        // TODO check build config if (BuildConfig.DEBUG.not()) return
        Log.v(
            tag = tag ?: DEFAULT_TAG,
            messageString = message
        )
    }

    fun tag(tag: String) = AppLogger(tag)
}

