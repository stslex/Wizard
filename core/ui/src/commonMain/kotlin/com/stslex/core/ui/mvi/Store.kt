package com.stslex.core.ui.mvi

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Stable
import com.stslex.core.ui.components.SnackbarType

interface Store {

    interface State

    interface Event {

        @Stable
        sealed class Snackbar(
            open val message: String,
            open val duration: SnackbarDuration,
            open val withDismissAction: Boolean,
            val action: String,
        ) : Event {

            @Stable
            data class Error(
                override val message: String,
                override val duration: SnackbarDuration = SnackbarDuration.Short,
                override val withDismissAction: Boolean = false,
            ) : Snackbar(
                message = message,
                action = SnackbarType.ERROR.label,
                duration = duration,
                withDismissAction = withDismissAction
            )

            @Stable
            data class Success(
                override val message: String,
                override val duration: SnackbarDuration = SnackbarDuration.Short,
                override val withDismissAction: Boolean = false,
            ) : Snackbar(
                message = message,
                action = SnackbarType.SUCCESS.label,
                duration = duration,
                withDismissAction = withDismissAction
            )

            @Stable
            data class Info(
                override val message: String,
                override val duration: SnackbarDuration = SnackbarDuration.Short,
                override val withDismissAction: Boolean = false,
            ) : Snackbar(
                message = message,
                action = SnackbarType.INFO.label,
                duration = duration,
                withDismissAction = withDismissAction
            )
        }
    }

    interface Navigation

    interface Action {
        interface RepeatLastAction : Action
    }
}
