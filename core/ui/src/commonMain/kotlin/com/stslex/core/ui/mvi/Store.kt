package com.stslex.core.ui.mvi

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Stable
import com.stslex.core.ui.components.SnackbarType
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface Store<S : Store.State, E : Store.Event, A : Store.Action> {

    /** Flow of the state of the screen. */
    val state: StateFlow<S>

    /** Flow of events that are sent to the screen. */
    val event: SharedFlow<E>

    /**
     * Sends an action to the store. Checks if the action is not the same as the last action.
     * If the action is not the same as the last action, the last action is updated.
     * The action is then processed.
     * @param action - action to be sent
     */
    fun sendAction(action: A)

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
