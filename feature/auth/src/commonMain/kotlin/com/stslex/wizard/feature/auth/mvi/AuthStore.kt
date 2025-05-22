package com.stslex.wizard.feature.auth.mvi

import com.stslex.wizard.core.ui.mvi.CommonEvents
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.auth.mvi.AuthStore.Action
import com.stslex.wizard.feature.auth.mvi.AuthStore.Event
import com.stslex.wizard.feature.auth.mvi.AuthStore.State

interface AuthStore : Store<State, Action, Event> {

    data class State(
        val screenLoadingState: ScreenLoadingState,
        val login: String,
        val username: String,
        val password: String,
        val passwordSubmit: String,
        val authFieldsState: AuthFieldsState
    ) : Store.State {
        companion object {
            val INITIAL = State(
                screenLoadingState = ScreenLoadingState.Content,
                login = "",
                username = "",
                password = "",
                passwordSubmit = "",
                authFieldsState = AuthFieldsState.AUTH
            )
        }
    }

    sealed interface Event : Store.Event {

        data class ShowSnackbar(
            val snackbar: CommonEvents.Snackbar
        ) : Event
    }

    sealed interface Action : Store.Action {

        sealed interface Click : Action {

            data class OnSubmitClicked(
                val state: AuthFieldsState
            ) : Click

            data class OnAuthFieldChange(
                val targetState: AuthFieldsState
            ) : Click

        }


        sealed class InputAction(
            open val value: String
        ) : Action {

            data class LoginInput(
                override val value: String
            ) : InputAction(value)

            data class UsernameInput(
                override val value: String
            ) : InputAction(value)

            data class PasswordInput(
                override val value: String
            ) : InputAction(value)

            data class PasswordSubmitInput(
                override val value: String
            ) : InputAction(value)
        }

        sealed interface Navigation : Action, Store.Action.Navigation {

            data object HomeFeature : Navigation
        }
    }

    // TODO get from resources
    enum class AuthFieldsState(
        val buttonRes: String,
        val titleRes: String
    ) {
        AUTH(
            buttonRes = "log in (if not have account)",
            titleRes = "Auth"
        ),
        REGISTER(
            buttonRes = "register(if not have account)",
            titleRes = "Register"
        );
    }

    sealed interface ScreenLoadingState {

        data object Loading : ScreenLoadingState

        data object Content : ScreenLoadingState

        data class Error(
            val error: Throwable
        ) : ScreenLoadingState
    }
}