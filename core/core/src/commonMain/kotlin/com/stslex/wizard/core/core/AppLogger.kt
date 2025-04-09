package com.stslex.wizard.core.core

class AppLogger(
    private val tag: String
) {

    fun d(message: String) {
        Logger.d(
            tag = tag,
            message = message
        )
    }

    fun e(
        throwable: Throwable,
        message: String? = null
    ) {
        Logger.e(
            tag = tag,
            throwable = throwable,
            message = message ?: throwable.message.orEmpty(),
        )
    }

    fun i(message: String) {
        Logger.i(
            tag = tag,
            message = message
        )
    }

    fun v(message: String) {
        Logger.v(
            tag = tag,
            message = message
        )
    }

}