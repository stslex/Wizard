package com.stslex.core.core

import co.touchlab.kermit.Logger as Log

object Logger {

    private const val DEFAULT_TAG = "WIZARD"

    fun exception(
        throwable: Throwable,
        tag: String? = null,
        message: String? = null
    ) {
        // TODO check build config if (BuildConfig.DEBUG.not()) return
        val currentTag = "$DEFAULT_TAG:${tag.orEmpty()}"
        Log.e(
            tag = currentTag,
            throwable = throwable,
            messageString = message ?: throwable.message.orEmpty(),
        )
    }

    fun debug(
        message: String,
        tag: String? = null,
    ) {
        // TODO check build config if (BuildConfig.DEBUG.not()) return
        val currentTag = "$DEFAULT_TAG:${tag.orEmpty()}"
        Log.d(
            tag = currentTag,
            messageString = message
        )
    }
}