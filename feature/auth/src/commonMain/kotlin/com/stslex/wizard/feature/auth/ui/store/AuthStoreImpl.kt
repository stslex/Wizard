package com.stslex.wizard.feature.auth.ui.store

import com.stslex.wizard.core.ui.mvi.BaseStore
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.feature.auth.domain.AuthInteractor
import com.stslex.wizard.feature.auth.navigation.AuthRouter
import com.stslex.wizard.feature.auth.ui.store.AuthStore.Action
import com.stslex.wizard.feature.auth.ui.store.AuthStore.AuthFieldsState
import com.stslex.wizard.feature.auth.ui.store.AuthStore.Event
import com.stslex.wizard.feature.auth.ui.store.AuthStore.ScreenLoadingState
import com.stslex.wizard.feature.auth.ui.store.AuthStore.State
import kotlinx.coroutines.delay

class AuthStoreImpl(
    private val interactor: AuthInteractor,
    private val router: AuthRouter
) : BaseStore<State, Action, Event>(State.INITIAL), AuthStore {

    override fun process(action: Action) {
        when (action) {
            is Action.InputAction.PasswordInput -> processPasswordInput(action)
            is Action.InputAction.PasswordSubmitInput -> processPasswordSubmitInput(action)
            is Action.InputAction.LoginInput -> processLoginInput(action)
            is Action.InputAction.UsernameInput -> processUsernameInput(action)
            is Action.OnAuthFieldChange -> processAuthFieldChange(action)
            is Action.OnSubmitClicked -> processSubmitClicked(action)
            is Action.Navigation -> router(action)
        }
    }

    private fun processLoginInput(action: Action.InputAction.LoginInput) {
        updateState { currentValue ->
            currentValue.copy(
                login = action.value
            )
        }
    }

    private fun processUsernameInput(action: Action.InputAction.UsernameInput) {
        updateState { currentValue ->
            currentValue.copy(
                username = action.value
            )
        }
    }

    private fun processPasswordInput(action: Action.InputAction.PasswordInput) {
        updateState { currentValue ->
            currentValue.copy(
                password = action.value
            )
        }
    }

    private fun processPasswordSubmitInput(action: Action.InputAction.PasswordSubmitInput) {
        updateState { currentValue ->
            currentValue.copy(
                passwordSubmit = action.value,
            )
        }
    }

    private fun processAuthFieldChange(action: Action.OnAuthFieldChange) {
        updateState { currentValue ->
            currentValue.copy(
                authFieldsState = action.targetState
            )
        }
    }

    private fun processSubmitClicked(action: Action.OnSubmitClicked) {
        when (action.state) {
            AuthFieldsState.AUTH -> auth()
            AuthFieldsState.REGISTER -> register()
        }
    }

    private fun register() {
        val state = state.value
        setLoadingState(ScreenLoadingState.Loading)

        launch(
            action = {
                interactor.register(
                    login = state.login,
                    username = state.username,
                    password = state.password
                )
            },
            onError = { error ->
                sendEvent(
                    Event.ShowSnackbar(
                        snackbar = Snackbar.Error(
                            message = error.message ?: "Unknown error"
                        )
                    )
                )
                setLoadingState(ScreenLoadingState.Content)
            },
            onSuccess = {
                sendEvent(
                    Event.ShowSnackbar(
                        snackbar = Snackbar.Success(
                            message = "Success register"
                        )
                    )
                )
                delay(1000L)
                updateState { currentState ->
                    currentState.copy(
                        screenLoadingState = ScreenLoadingState.Content,
                        authFieldsState = AuthFieldsState.AUTH
                    )
                }
                consume(Action.Navigation.HomeFeature)
            })
    }

    private fun auth() {
        val state = state.value
        setLoadingState(ScreenLoadingState.Loading)
        launch(
            action = {
                interactor.auth(
                    login = state.login,
                    password = state.password
                )
            },
            onError = { error ->
                sendEvent(
                    Event.ShowSnackbar(
                        snackbar = Snackbar.Error(
                            message = error.message ?: "Unknown error"
                        )
                    )
                )
                setLoadingState(ScreenLoadingState.Content)
            },
            onSuccess = {
                sendEvent(
                    Event.ShowSnackbar(
                        snackbar = Snackbar.Success(
                            message = "Success auth"
                        )
                    )
                )
                delay(1000L)
                updateState { currentState ->
                    currentState.copy(
                        screenLoadingState = ScreenLoadingState.Content,
                        authFieldsState = AuthFieldsState.AUTH
                    )
                }
                consume(Action.Navigation.HomeFeature)
            })
    }

    private fun setLoadingState(screenLoadingState: ScreenLoadingState) {
        updateState { state ->
            state.copy(
                screenLoadingState = screenLoadingState
            )
        }
    }
}

