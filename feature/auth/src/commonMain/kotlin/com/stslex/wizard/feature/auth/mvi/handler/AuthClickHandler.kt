package com.stslex.wizard.feature.auth.mvi.handler

import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.auth.di.AuthScope
import com.stslex.wizard.feature.auth.domain.AuthInteractor
import com.stslex.wizard.feature.auth.mvi.AuthHandlerStore
import com.stslex.wizard.feature.auth.mvi.AuthStore
import com.stslex.wizard.feature.auth.mvi.AuthStore.Action.Click
import kotlinx.coroutines.delay
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(AuthScope::class)
@Scoped
internal class AuthClickHandler(
    private val interactor: AuthInteractor
) : Handler<Click, AuthHandlerStore> {

    override fun AuthHandlerStore.invoke(action: Click) {
        when (action) {
            is Click.OnAuthFieldChange -> processAuthFieldChange(action)
            is Click.OnSubmitClicked -> processSubmitClicked(action)
        }
    }

    private fun AuthHandlerStore.processAuthFieldChange(action: Click.OnAuthFieldChange) {
        updateState { currentValue ->
            currentValue.copy(
                authFieldsState = action.targetState
            )
        }
    }

    private fun AuthHandlerStore.processSubmitClicked(action: Click.OnSubmitClicked) {
        when (action.state) {
            AuthStore.AuthFieldsState.AUTH -> auth()
            AuthStore.AuthFieldsState.REGISTER -> register()
        }
    }

    private fun AuthHandlerStore.register() {
        val state = state.value
        setLoadingState(AuthStore.ScreenLoadingState.Loading)

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
                    AuthStore.Event.ShowSnackbar(
                        snackbar = Snackbar.Error(
                            message = error.message ?: "Unknown error"
                        )
                    )
                )
                setLoadingState(AuthStore.ScreenLoadingState.Content)
            },
            onSuccess = {
                sendEvent(
                    AuthStore.Event.ShowSnackbar(
                        snackbar = Snackbar.Success(
                            message = "Success register"
                        )
                    )
                )
                delay(1000L)
                updateState { currentState ->
                    currentState.copy(
                        screenLoadingState = AuthStore.ScreenLoadingState.Content,
                        authFieldsState = AuthStore.AuthFieldsState.AUTH
                    )
                }
                consume(AuthStore.Action.Navigation.HomeFeature)
            })
    }

    private fun AuthHandlerStore.auth() {
        val state = state.value
        setLoadingState(AuthStore.ScreenLoadingState.Loading)
        launch(
            action = {
                interactor.auth(
                    login = state.login,
                    password = state.password
                )
            },
            onError = { error ->
                sendEvent(
                    AuthStore.Event.ShowSnackbar(
                        snackbar = Snackbar.Error(
                            message = error.message ?: "Unknown error"
                        )
                    )
                )
                setLoadingState(AuthStore.ScreenLoadingState.Content)
            },
            onSuccess = {
                sendEvent(
                    AuthStore.Event.ShowSnackbar(
                        snackbar = Snackbar.Success(
                            message = "Success auth"
                        )
                    )
                )
                delay(1000L)
                updateState { currentState ->
                    currentState.copy(
                        screenLoadingState = AuthStore.ScreenLoadingState.Content,
                        authFieldsState = AuthStore.AuthFieldsState.AUTH
                    )
                }
                consume(AuthStore.Action.Navigation.HomeFeature)
            })
    }

    private fun AuthHandlerStore.setLoadingState(screenLoadingState: AuthStore.ScreenLoadingState) {
        updateState { state ->
            state.copy(
                screenLoadingState = screenLoadingState
            )
        }
    }
}