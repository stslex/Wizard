package com.stslex.feature.auth.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.auth.domain.AuthInteractor
import com.stslex.feature.auth.navigation.AuthRouter
import com.stslex.feature.auth.ui.store.AuthStoreComponent.Action
import com.stslex.feature.auth.ui.store.AuthStoreComponent.AuthFieldsState
import com.stslex.feature.auth.ui.store.AuthStoreComponent.Event
import com.stslex.feature.auth.ui.store.AuthStoreComponent.Navigation
import com.stslex.feature.auth.ui.store.AuthStoreComponent.ScreenLoadingState
import com.stslex.feature.auth.ui.store.AuthStoreComponent.State

class AuthStore(
    private val interactor: AuthInteractor,
    dispatcher: AppDispatcher,
    router: AuthRouter
) : BaseStore<State, Event, Action, Navigation>(
    appDispatcher = dispatcher,
    initialState = State.INITIAL,
    router = router
) {

    override fun sendAction(action: Action) {
        when (action) {
            is Action.InputAction.PasswordInput -> processPasswordInput(action)
            is Action.InputAction.PasswordSubmitInput -> processPasswordSubmitInput(action)
            is Action.InputAction.LoginInput -> processLoginInput(action)
            is Action.InputAction.UsernameInput -> processUsernameInput(action)
            is Action.OnAuthFieldChange -> processAuthFieldChange(action)
            is Action.OnSubmitClicked -> processSubmitClicked(action)
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
            onError = {
                setLoadingState(ScreenLoadingState.Content)
                // TODO sendEvent(Event.ShowSnackbar.ERROR)
            },
            onSuccess = {
                updateState { currentState ->
                    currentState.copy(
                        screenLoadingState = ScreenLoadingState.Content,
                        authFieldsState = AuthFieldsState.AUTH
                    )
                }
                // TODO sendEvent(Event.ShowSnackbar.SuccessRegister)
            }) {
            interactor
                .register(
                    login = state.login,
                    username = state.username,
                    password = state.password
                )
        }
    }

    private fun auth() {
        val state = state.value
        setLoadingState(ScreenLoadingState.Loading)
        launch(
            onError = {
                setLoadingState(ScreenLoadingState.Content)
                // TODO sendEvent(Event.ShowSnackbar.ERROR)
            },
            onSuccess = {
                updateState { currentState ->
                    currentState.copy(
                        screenLoadingState = ScreenLoadingState.Content,
                        authFieldsState = AuthFieldsState.AUTH
                    )
                }
                // TODO sendEvent(Event.ShowSnackbar.SuccessRegister)
            }) {
            interactor
                .auth(
                    login = state.login,
                    password = state.password
                )
        }
    }

    private fun setLoadingState(screenLoadingState: ScreenLoadingState) {
        updateState { state ->
            state.copy(
                screenLoadingState = screenLoadingState
            )
        }
    }
}

