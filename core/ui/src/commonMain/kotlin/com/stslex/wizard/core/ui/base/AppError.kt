package com.stslex.wizard.core.ui.base

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.network.api.server.model.ErrorRefresh

@Stable
sealed class AppError(open val message: String) {

    @Stable
    data class AuthError(
        override val message: String
    ) : AppError(message)

    @Stable
    data class OtherError(
        override val message: String
    ) : AppError(message)
}

fun Throwable.mapToAppError(
    otherMessage: String = "unknown error"
): AppError {
    return when (this) {
        is ErrorRefresh -> AppError.AuthError(message ?: otherMessage)
        else -> AppError.OtherError(message ?: otherMessage)
    }
}
