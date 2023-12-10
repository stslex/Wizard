package com.stslex.feature.auth.ui.store

import com.stslex.core.ui.mvi.Store

interface AuthStoreComponent : Store {

    data class State(
        val screenLoadingState: ScreenLoadingState,
        val username: String,
        val password: String,
        val passwordSubmit: String,
        val authFieldsState: AuthFieldsState
    ) : Store.State {
        companion object {
            val INITIAL = State(
                screenLoadingState = ScreenLoadingState.Content,
                username = "",
                password = "",
                passwordSubmit = "",
                authFieldsState = AuthFieldsState.AUTH
            )
        }
    }

    sealed interface Event : Store.Event

    sealed interface Action : Store.Action {
        data class OnSubmitClicked(
            val state: AuthFieldsState
        ) : Action

        data class OnAuthFieldChange(
            val targetState: AuthFieldsState
        ) : Action

        sealed class InputAction(
            open val value: String
        ) : Action {

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
    }

    sealed interface Navigation : Store.Navigation {

        data object HomeFeature : Navigation
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